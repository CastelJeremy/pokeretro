package net.pokeretro.trainer.controller;

import net.pokeretro.trainer.trainer.Trainer;
import net.pokeretro.trainer.trainer.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

@RestController
public class TrainerController {
    @Autowired
    private TrainerService service;
    @RequestMapping(value="/create", method = RequestMethod.POST)
    public ResponseEntity<String> create (@RequestParam(value = "username", required = false, defaultValue = "-1") String username,
                                          @RequestParam(value = "gender", required = false, defaultValue = "-1") String gender,
                                          @RequestParam(value = "starter", required = false, defaultValue = "-1") int starter)
    {
        if (!Objects.equals(gender, "boy") && !Objects.equals(gender, "girl"))
            return new ResponseEntity<>("BAD_GENDER", HttpStatus.BAD_REQUEST);
        else if (!Objects.equals(starter, 1) && !Objects.equals(starter, 4) && !Objects.equals(starter, 7))
            return new ResponseEntity<>("BAD_STARTER", HttpStatus.BAD_REQUEST);
        else {
            Trainer newTrainer = new Trainer(username, gender, starter);
            service.saveTrainer(newTrainer);
            return new ResponseEntity<>("SAVE_USER_DONE", HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/trainer/{id}", method = RequestMethod.GET)
    public Collection<Trainer> giveTrainerById (@PathVariable("id") UUID id)
    {
        if(id != null)
            return service.getTrainer(id).stream().toList();
        else
            return service.getTrainers();
    }
}