package net.pokeretro.trainer.controller;

import net.pokeretro.trainer.trainer.Trainer;
import net.pokeretro.trainer.trainer.TrainerRepository;
import net.pokeretro.trainer.trainer.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RestController
public class TrainerController {
    @Autowired
    private TrainerService service;
    @RequestMapping(value="/create", method = RequestMethod.POST)
    public ResponseEntity<Trainer> create (@RequestBody Trainer trainer)
    {
        if (!Objects.equals(trainer.getGender(), "boy") && !Objects.equals(trainer.getGender(), "girl"))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        else if (!Objects.equals(trainer.getStarter(), 1) && !Objects.equals(trainer.getStarter(), 4) && !Objects.equals(trainer.getStarter(), 7))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        else {
            Trainer newTrainer = new Trainer(trainer.getUsername(), trainer.getGender(), trainer.getStarter());
            service.saveTrainer(newTrainer);
            if (service.getTrainer(newTrainer.getId()).isPresent())
                return ResponseEntity.ok(service.getTrainer(newTrainer.getId()).get());
            else
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/trainer/{id}", method = RequestMethod.GET)
    public Collection<Trainer> giveTrainerById (@PathVariable("id") UUID id)
    {
        if(service.getTrainer(id).stream().toList().isEmpty())
            return service.getTrainers().stream().toList();
        else
            return service.getTrainer(id).stream().toList();
    }
}