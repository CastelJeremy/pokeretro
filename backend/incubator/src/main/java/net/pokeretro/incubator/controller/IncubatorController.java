package net.pokeretro.incubator.controller;

import net.pokeretro.incubator.exception.NotEnoughPlaceException;
import net.pokeretro.incubator.exception.NotReadyToHatchException;
import net.pokeretro.incubator.model.*;
import net.pokeretro.incubator.respositories.EggRepository;
import net.pokeretro.incubator.service.IncubatorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class IncubatorController {
    @Autowired
    private EggRepository eggRepository;

    @Autowired
    private IncubatorService incubatorService;

    @GetMapping("/incubator/{trainerId}")
    public ResponseEntity<List<Egg>> getIncubator(@PathVariable UUID trainerId) {
        return ResponseEntity.ok(eggRepository.findAllByTrainerId(trainerId));
    }

    @PostMapping("/incubator/place/{trainerId}")
    public ResponseEntity<List<Egg>> placeEgg(@PathVariable UUID trainerId, @RequestBody Egg egg)
            throws NotEnoughPlaceException {
        return ResponseEntity.ok(incubatorService.place(trainerId, egg));
    }

    @PostMapping("/incubator/hatch/{trainerId}")
    public ResponseEntity<List<Egg>> hatchEgg(@PathVariable UUID trainerId, @RequestBody Egg egg)
            throws NotReadyToHatchException {
        Optional<Egg> optEgg = eggRepository.findByIdAndTrainerId(egg.getId(), trainerId);

        if (optEgg.isPresent()) {
            return ResponseEntity.ok(incubatorService.hatch(trainerId, optEgg.get()));
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
