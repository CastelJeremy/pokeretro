package net.pokeretro.team.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import net.pokeretro.team.dto.TeammateDTO;
import net.pokeretro.team.entity.Teammate;
import net.pokeretro.team.repository.TeammateRepository;

@RestController
public class TeammateController {
    @Autowired
    TeammateRepository teammateRepository;

    @CrossOrigin
    @PutMapping("/teammate/{trainerUuid}")
    public ResponseEntity<TeammateDTO> putTeammate(@PathVariable UUID trainerUuid, @RequestBody TeammateDTO teammate) {
        if (teammate.getId() != null) {
            Optional<Teammate> res = teammateRepository.findById(teammate.getId());

            // Check ownership
            if (res.isPresent() && res.get().getTrainerUuid().equals(trainerUuid)) {
                System.out.println(teammate.getId());
                Teammate t = res.get();

                if (teammate.getName() != null && !teammate.getName().isEmpty()) {
                    t.setName(teammate.getName());
                }

                if (teammate.getLevel() != null && teammate.getLevel() > t.getLevel()) {
                    t.setLevel(teammate.getLevel());
                }

                if (teammate.getXp() != null) {
                    t.setXp(teammate.getXp());
                }

                // handle evolution

                // handle ev

                // handle capacity

                teammateRepository.save(t);

                return ResponseEntity.ok(t.toDto());
            }

            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
}
