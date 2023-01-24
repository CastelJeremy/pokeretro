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

import net.pokeretro.team.entity.Teammate;
import net.pokeretro.team.repository.TeammateRepository;

@RestController
public class TeammateController {
    @Autowired
    TeammateRepository teammateRepository;

    @CrossOrigin
    @PutMapping("/teammate/{id}")
    public ResponseEntity<Teammate> putTeammate(@PathVariable UUID id, @RequestBody Teammate teammate) {
        if (teammate.getId() != null) {
            Optional<Teammate> res = teammateRepository.findById(teammate.getId());

            // Check trainerUuid
            if (res.isPresent()) {
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

                // handle capacity

                teammateRepository.save(t);

                return ResponseEntity.ok(t);
            }

            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
}
