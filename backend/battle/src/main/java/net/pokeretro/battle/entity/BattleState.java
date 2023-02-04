package net.pokeretro.battle.entity;

import java.util.List;
import java.util.UUID;

import org.springframework.data.redis.core.RedisHash;

import net.pokeretro.battle.dto.BattleStateDTO;

@RedisHash("BattleState")
public class BattleState {
    private UUID id;
    private Integer turn = 0;
    private String status = "idle";
    private Integer currentFighter;
    private Integer currentOpponent;
    private List<Teammate> fighterTeam;
    private List<Teammate> opponentTeam;
    private Integer attemps = 0;

    public BattleState() {
    }

    public BattleState(UUID id, Integer currentFighter, Integer currentOpponent,
            List<Teammate> fighterTeam, List<Teammate> opponentTeam) {
        this.id = id;
        this.currentFighter = currentFighter;
        this.currentOpponent = currentOpponent;
        this.fighterTeam = fighterTeam;
        this.opponentTeam = opponentTeam;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getTurn() {
        return turn;
    }

    public void setTurn(Integer turn) {
        this.turn = turn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCurrentFighter() {
        return currentFighter;
    }

    public void setCurrentFighter(Integer currentFighter) {
        this.currentFighter = currentFighter;
    }

    public Integer getCurrentOpponent() {
        return currentOpponent;
    }

    public void setCurrentOpponent(Integer currentOpponent) {
        this.currentOpponent = currentOpponent;
    }

    public List<Teammate> getFighterTeam() {
        return fighterTeam;
    }

    public List<Teammate> getOpponentTeam() {
        return opponentTeam;
    }

    public Integer getAttemps() {
        return attemps;
    }

    public void setAttemps(Integer attemps) {
        this.attemps = attemps;
    }

    public BattleStateDTO toDto() {
        return new BattleStateDTO(
                id,
                turn,
                status,
                currentFighter,
                currentOpponent,
                fighterTeam.stream().map(t -> t.toDto()).toList(),
                opponentTeam.stream().map(t -> t.toDto()).toList());
    }
}
