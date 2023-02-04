package net.pokeretro.battle.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import net.pokeretro.battle.dto.BattleStateDTO;
import net.pokeretro.battle.dto.TeammateDTO;
import net.pokeretro.battle.entity.BattleState;
import net.pokeretro.battle.entity.Capacity;
import net.pokeretro.battle.entity.Teammate;
import net.pokeretro.battle.exception.InvalidStateException;
import net.pokeretro.battle.repository.BattleStateRepository;
import net.pokeretro.battle.service.BattleStateService;

@RestController
public class BattleController {
    @Autowired
    BattleStateRepository battleStateRepository;

    @Autowired
    BattleStateService battleStateService;

    @CrossOrigin
    @PostMapping("/battle/{id}")
    public ResponseEntity<BattleStateDTO> postBattleUser(@PathVariable UUID id,
            @RequestBody BattleStateDTO battleStateDTO) {
        Optional<BattleState> battleState = battleStateRepository.findById(id);

        if (battleState.isEmpty()) {
            List<Teammate> fighterTeam = battleStateDTO.getFighterTeam().stream().map(t -> Teammate.fromDto(t))
                    .toList();
            List<Teammate> opponentTeam = battleStateDTO.getOpponentTeam().stream().map(t -> Teammate.fromDto(t))
                    .toList();

            try {
                BattleState newBattleState = battleStateService.create(id, fighterTeam, opponentTeam);

                return ResponseEntity.ok(newBattleState.toDto());
            } catch (InvalidStateException e) {
            }
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin
    @GetMapping("/battle/{id}")
    public ResponseEntity<BattleStateDTO> getBattle(@PathVariable UUID id) {
        Optional<BattleState> battleState = battleStateRepository.findById(id);

        if (battleState.isPresent()) {
            return ResponseEntity.ok(battleState.get().toDto());
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @CrossOrigin
    @PostMapping("/battle/{id}/escape")
    public ResponseEntity<BattleStateDTO> escapeBattle(@PathVariable UUID id) {
        Optional<BattleState> battleState = battleStateRepository.findById(id);

        if (battleState.isPresent()) {
            try {
                return ResponseEntity.ok(battleStateService.escape(battleState.get()).toDto());
            } catch (InvalidStateException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @CrossOrigin
    @PostMapping("/battle/{id}/use/{capacityId}")
    public ResponseEntity<BattleStateDTO> useCapacity(@PathVariable UUID id, @PathVariable Integer capacityId) {
        Optional<BattleState> optBattleState = battleStateRepository.findById(id);

        if (optBattleState.isPresent()) {
            BattleState battleState = optBattleState.get();
            List<Capacity> capacities = battleState.getFighterTeam().get(battleState.getCurrentFighter())
                    .getCapacities();

            if (capacityId < capacities.size()) {
                Capacity capacity = capacities.get(capacityId);

                try {
                    return ResponseEntity.ok(battleStateService.use(battleState, capacity).toDto());
                } catch (InvalidStateException e) {
                }
            }

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @CrossOrigin
    @PutMapping("/battle/{id}/swap")
    public ResponseEntity<BattleStateDTO> switchPokemon(@PathVariable UUID id, @RequestBody TeammateDTO teammateDTO) {
        Optional<BattleState> battleState = battleStateRepository.findById(id);

        if (battleState.isPresent()) {
            try {
                return ResponseEntity
                        .ok(battleStateService.swap(battleState.get(), Teammate.fromDto(teammateDTO)).toDto());
            } catch (InvalidStateException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @CrossOrigin
    @DeleteMapping("/battle/{id}")
    public ResponseEntity<BattleStateDTO> finishBattle(@PathVariable UUID id) {
        Optional<BattleState> battleState = battleStateRepository.findById(id);

        if (battleState.isPresent()) {
            try {
                return ResponseEntity.ok(battleStateService.delete(battleState.get()).toDto());
            } catch (InvalidStateException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }
}