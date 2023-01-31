package net.pokeretro.team.controller;

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

import net.pokeretro.team.dto.PokemonDTO;
import net.pokeretro.team.dto.TeammateDTO;
import net.pokeretro.team.entity.Teammate;
import net.pokeretro.team.repository.TeammateRepository;
import net.pokeretro.team.service.TeammateService;

@RestController
public class TeamController {
    @Autowired
    TeammateRepository teammateRepository;

    @Autowired
    TeammateService teammateService;

    @CrossOrigin
    @GetMapping("/team/{trainerUuid}")
    public ResponseEntity<List<TeammateDTO>> getTeam(@PathVariable UUID trainerUuid) {
        return ResponseEntity
                .ok(teammateRepository.findAllByTrainerUuid(trainerUuid).stream().map(t -> t.toDto())
                        .toList());
    }

    @CrossOrigin
    @PostMapping("/team/{trainerUuid}")
    public ResponseEntity<TeammateDTO> postTeam(@PathVariable UUID trainerUuid, @RequestBody PokemonDTO pokemonDto) {
        return ResponseEntity.ok(teammateService.addPokemon(trainerUuid, pokemonDto).toDto());
    }

    @CrossOrigin
    @DeleteMapping("/team/{trainerUuid}")
    public ResponseEntity<List<TeammateDTO>> deleteTeam(@PathVariable UUID trainerUuid,
            @RequestBody Teammate teammate) {
        if (teammate.getId() != null) {
            Optional<Teammate> res = teammateRepository.findById(teammate.getId());

            if (res.isPresent() && res.get().getTrainerUuid().equals(trainerUuid)) {
                return ResponseEntity.ok(teammateService.removeTeammate(trainerUuid, res.get()).stream()
                        .map(t -> t.toDto()).toList());
            }

            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin
    @PutMapping("/team/{trainerUuid}/heal")
    public ResponseEntity<List<TeammateDTO>> putTeamHeal(@PathVariable UUID trainerUuid) {
        return ResponseEntity.ok(teammateService.healAll(trainerUuid).stream().map(t -> t.toDto()).toList());
    }

    @CrossOrigin
    @PutMapping("/team/{trainerUuid}/swap")
    public ResponseEntity<List<TeammateDTO>> putTeamSwap(@PathVariable UUID trainerUuid,
            @RequestBody List<TeammateDTO> teammates) {
        if (teammates.size() == 2 && teammates.get(0).getId() != null && teammates.get(1).getId() != null
                && teammates.get(0).getId() != teammates.get(1).getId()) {
            Optional<Teammate> res1 = teammateRepository.findById(teammates.get(0).getId());
            Optional<Teammate> res2 = teammateRepository.findById(teammates.get(1).getId());

            if (res1.isPresent() && res1.get().getTrainerUuid().equals(trainerUuid) && res2.isPresent()
                    && res2.get().getTrainerUuid().equals(trainerUuid)) {
                return ResponseEntity.ok(teammateService.swapTeammates(trainerUuid, res1.get(), res2.get()).stream()
                        .map(t -> t.toDto()).toList());
            }

            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
}
