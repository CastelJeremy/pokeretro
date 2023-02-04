package net.pokeretro.battle.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.pokeretro.battle.entity.BattleState;
import net.pokeretro.battle.entity.Capacity;
import net.pokeretro.battle.entity.Teammate;
import net.pokeretro.battle.exception.InvalidStateException;
import net.pokeretro.battle.repository.BattleStateRepository;

@Service
public class BattleStateService {
    @Autowired
    BattleStateRepository battleStateRepository;

    public BattleState create(UUID trainerId, List<Teammate> fighterTeam, List<Teammate> opponentTeam)
            throws InvalidStateException {
        Integer currentFighter = 0;
        while (fighterTeam.get(currentFighter).getCurrentStat().getHp() == 0 && currentFighter < fighterTeam.size()) {
            currentFighter++;
        }

        if (currentFighter == 0 && fighterTeam.get(currentFighter).getCurrentStat().getHp() == 0) {
            throw new InvalidStateException();
        }

        Integer currentOpponent = 0;
        while (opponentTeam.get(currentOpponent).getCurrentStat().getHp() == 0
                && currentOpponent < opponentTeam.size()) {
            currentOpponent++;
        }

        if (currentOpponent == 0 && opponentTeam.get(currentOpponent).getCurrentStat().getHp() == 0) {
            throw new InvalidStateException();
        }

        BattleState battleState = new BattleState(trainerId, currentFighter, currentOpponent, fighterTeam,
                opponentTeam);

        battleStateRepository.save(battleState);

        return battleState;
    }

    public BattleState escape(BattleState battleState) throws InvalidStateException {
        if (battleState.getStatus().equals("won") || battleState.getStatus().equals("lost")
                || battleState.getStatus().equals("escaped") || battleState.getStatus().equals("fainted")) {
            throw new InvalidStateException();
        }

        Teammate fighter = battleState.getFighterTeam().get(battleState.getCurrentFighter());
        Teammate opponent = battleState.getOpponentTeam().get(battleState.getCurrentOpponent());

        Integer opponentOdds = Math.floorDiv(opponent.getCurrentStat().getSpeed(), 4) % 256;
        Integer odds = Math.floorDiv(fighter.getCurrentStat().getSpeed() * 32, opponentOdds)
                + 30 * battleState.getAttemps();

        Random rand = new Random();
        Integer dice = rand.nextInt(256);

        if (opponentOdds == 0 || odds > 255 || dice < odds) {
            battleState.setStatus("escaped");
        } else {
            battleState.setAttemps(battleState.getAttemps() + 1);
            processOpponentTurn(battleState);
        }

        battleStateRepository.save(battleState);

        return battleState;
    }

    public BattleState use(BattleState battleState, Capacity capacity) throws InvalidStateException {
        if (battleState.getStatus().equals("won") || battleState.getStatus().equals("lost")
                || battleState.getStatus().equals("escaped") || battleState.getStatus().equals("fainted")) {
            throw new InvalidStateException();
        }

        if (capacity.getUseCount() == capacity.getPp() || battleState.getStatus().equals("fainted")) {
            throw new InvalidStateException();
        }

        Teammate fighter = battleState.getFighterTeam().get(battleState.getCurrentFighter());
        Teammate opponent = battleState.getOpponentTeam().get(battleState.getCurrentOpponent());

        battleState.setAttemps(0);
        if (doesCapacityHits(capacity)) {
            if (capacity.getCategory().equals("physical") || capacity.getCategory().equals("special")) {
                Integer damage = processDamage(fighter, opponent, capacity);
                Integer newHp = opponent.getCurrentStat().getHp() - damage;

                if (newHp < 0) {
                    newHp = 0;
                }

                opponent.getCurrentStat().setHp(newHp);
            }
        }

        capacity.setUseCount(capacity.getUseCount() + 1);

        processOpponentTurn(battleState);

        battleStateRepository.save(battleState);

        return battleState;
    }

    public BattleState swap(BattleState battleState, Teammate teammate) throws InvalidStateException {
        if (battleState.getStatus().equals("won") || battleState.getStatus().equals("lost")
                || battleState.getStatus().equals("escaped")) {
            throw new InvalidStateException();
        }

        Optional<Teammate> optTeammate = battleState.getFighterTeam().stream()
                .filter(t -> t.getId().equals(teammate.getId())).findFirst();

        if (optTeammate.isEmpty() || optTeammate.get().getCurrentStat().getHp() == 0) {
            throw new InvalidStateException();
        }

        Integer fighterIndex = battleState.getFighterTeam().indexOf(optTeammate.get());

        if (fighterIndex == battleState.getCurrentFighter()) {
            throw new InvalidStateException();
        }

        // WTF ?
        if (fighterIndex == -1) {
            throw new InvalidStateException();
        }

        battleState.setCurrentFighter(fighterIndex);

        if (!battleState.getStatus().equals("fainted")) {
            processOpponentTurn(battleState);
        } else {
            battleState.setStatus("idle");
        }

        battleStateRepository.save(battleState);

        return battleState;
    }

    public BattleState delete(BattleState battleState) throws InvalidStateException {
        if (!battleState.getStatus().equals("won") && !battleState.getStatus().equals("lost")
                && !battleState.getStatus().equals("escaped")) {
            throw new InvalidStateException();
        }

        battleStateRepository.delete(battleState);

        return battleState;
    }

    private void processOpponentTurn(BattleState battleState) {
        Teammate fighter = battleState.getFighterTeam().get(battleState.getCurrentFighter());
        Teammate opponent = battleState.getOpponentTeam().get(battleState.getCurrentOpponent());
        Random rand = new Random();

        battleState.setStatus("idle");

        if (opponent.getCurrentStat().getHp() == 0) {
            List<Teammate> alive = battleState.getOpponentTeam().stream().filter(t -> t.getCurrentStat().getHp() > 0)
                    .toList();

            if (alive.size() > 0) {
                Integer currentOpponent = battleState.getOpponentTeam().indexOf(alive.get(rand.nextInt(alive.size())));
                battleState.setCurrentOpponent(currentOpponent);
            } else {
                battleState.setStatus("won");
            }
        } else {
            List<Capacity> usableCapacities = opponent.getCapacities().stream().filter(c -> c.getUseCount() < c.getPp())
                    .toList();
            Capacity capacity = usableCapacities.get(rand.nextInt(usableCapacities.size()));

            if (doesCapacityHits(capacity)) {
                if (capacity.getCategory().equals("physical") || capacity.getCategory().equals("special")) {
                    Integer damage = processDamage(opponent, fighter, capacity);
                    Integer newHp = fighter.getCurrentStat().getHp() - damage;

                    if (newHp < 0) {
                        newHp = 0;
                    }

                    fighter.getCurrentStat().setHp(newHp);

                    if (newHp == 0) {
                        battleState.setStatus("fainted");
                    }
                }
            }

            capacity.setUseCount(capacity.getUseCount() + 1);

            List<Teammate> alive = battleState.getFighterTeam().stream().filter(t -> t.getCurrentStat().getHp() > 0)
                    .toList();

            if (alive.size() == 0) {
                battleState.setStatus("lost");
            }
        }
    }

    private Boolean doesCapacityHits(Capacity capacity) {
        Integer accuracy = capacity.getAccuracy();

        if (accuracy == null) {
            accuracy = 100;
        }

        Random rand = new Random();
        Integer odds = accuracy * 100 * 100;
        Integer dice = rand.nextInt(256);

        return (dice < odds);
    }

    private Integer processDamage(Teammate attacker, Teammate defender, Capacity capacity) {
        Integer attack = attacker.getCurrentStat().getAttack();
        Integer defense = defender.getCurrentStat().getDefense();
        Integer level = attacker.getLevel();
        Integer power = capacity.getPower();

        if (power == null) {
            power = 100;
        }

        Float stab = 1f;

        if (attacker.getPokemon().getTypes().contains(capacity.getType())) {
            stab = 1.5f;
        }

        if (capacity.getCategory().equals("special")) {
            attack = attacker.getCurrentStat().getSpecial();
            defense = defender.getCurrentStat().getSpecial();
        }

        if (attack > 255 || defense > 255) {
            attack = Math.floorDiv(attack, 4);
            defense = Math.floorDiv(defense, 4);
        }

        Integer damage = (2 * level / 5) + 2;
        damage = damage * power * (attack / defense);
        damage = damage / 50 + 2;
        damage = (int) Math.floor(damage * stab);

        return damage;
    }
}
