package net.pokeretro.arena.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import net.pokeretro.arena.dto.BattleStateDTO;
import net.pokeretro.arena.dto.BossDTO;
import net.pokeretro.arena.entity.Boss;
import net.pokeretro.arena.entity.TrainerBoss;
import net.pokeretro.arena.exception.AlreadyInBattleException;
import net.pokeretro.arena.exception.ChampionException;
import net.pokeretro.arena.exception.ImpossibleException;
import net.pokeretro.arena.exception.TrainerNotExistsException;
import net.pokeretro.arena.repository.BossRepository;
import net.pokeretro.arena.repository.TrainerBossRepository;
import net.pokeretro.arena.service.TrainerBossService;

@RestController
public class ArenaController {
    @Autowired
    BossRepository bossRepository;

    @Autowired
    TrainerBossRepository trainerBossRepository;

    @Autowired
    TrainerBossService trainerBossService;

    @GetMapping("/arena/boss/{trainerId}")
    public ResponseEntity<BossDTO> getBoss(@PathVariable("trainerId") UUID trainerId) {
        Optional<TrainerBoss> optTrainerBoss = trainerBossRepository.findById(trainerId);

        if (optTrainerBoss.isPresent()) {
            if (optTrainerBoss.get().getBoss() == null) {
                return ResponseEntity.ok(new Boss().toDto());
            }

            return ResponseEntity.ok(optTrainerBoss.get().getBoss().toDto());
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/arena/boss/{trainerId}")
    public ResponseEntity<BattleStateDTO> startBoss(@PathVariable("trainerId") UUID trainerId)
            throws ImpossibleException {
        try {
            return ResponseEntity.ok(trainerBossService.fightBoss(trainerId));
        } catch (TrainerNotExistsException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch (AlreadyInBattleException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (ChampionException e) {
            throw new ResponseStatusException(HttpStatus.ACCEPTED);
        }
    }

    @PostMapping("/arena/pokemon/{trainerId}")
    public ResponseEntity<BattleStateDTO> startPokemon(@PathVariable("trainerId") UUID trainerId) {
        try {
            return ResponseEntity.ok(trainerBossService.fightPokemon(trainerId));
        } catch (TrainerNotExistsException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch (AlreadyInBattleException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
