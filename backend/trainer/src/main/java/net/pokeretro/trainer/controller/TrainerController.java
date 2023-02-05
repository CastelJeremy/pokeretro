package net.pokeretro.trainer.controller;

import net.pokeretro.trainer.entity.Trainer;
import net.pokeretro.trainer.exception.TrainerCreateException;
import net.pokeretro.trainer.repository.TrainerRepository;
import net.pokeretro.trainer.service.TrainerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class TrainerController {
    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private TrainerService trainerService;

    @PostMapping("trainer")
    public ResponseEntity<Trainer> create(@RequestBody Trainer trainer) {
        try {
            return ResponseEntity.ok(trainerService.create(trainer));
        } catch (TrainerCreateException e) {

        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/trainer/{id}")
    public ResponseEntity<Trainer> getTrainerById(@PathVariable("id") UUID id) {
        Optional<Trainer> trainer = trainerRepository.findById(id);

        if (trainer.isPresent()) {
            return ResponseEntity.ok(trainer.get());
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/trainer/user/{userId}")
    public ResponseEntity<List<Trainer>> getTrainersByUserId(@PathVariable("userId") UUID userId) {
        List<Trainer> trainers = trainerRepository.findAllByUserId(userId);

        return ResponseEntity.ok(trainers);
    }
}