package net.pokeretro.incubator.controller;

import net.pokeretro.incubator.exception.NotEnoughPlaceException;
import net.pokeretro.incubator.model.*;
import net.pokeretro.incubator.respositories.EggRepository;
import net.pokeretro.incubator.respositories.IncubatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class IncubatorController {

    @Autowired
    private IncubatorRepository incubatorRepository;
    @Autowired
    private EggRepository eggRepository;

    @GetMapping(value = "/incubator")
    public ResponseEntity<IncubatorDTO> getIncubator(@RequestParam UUID idTrainer) {
        Optional<Incubator> optionalIncubator = incubatorRepository.findByIdTrainer(idTrainer);

        if(optionalIncubator.isEmpty()) {
            Incubator newIncubator = new Incubator(idTrainer);
            incubatorRepository.save(newIncubator);
            return ResponseEntity.ok(newIncubator.toDTO());
        } else {
            return ResponseEntity.ok(optionalIncubator.get().toDTO());
        }
    }

    @GetMapping(value = "/incubator/weight")
    public Integer getWeight(@RequestParam UUID idTrainer) {
        Optional<Incubator> optionalIncubator = incubatorRepository.findByIdTrainer(idTrainer);
        return optionalIncubator.map(Incubator::getWeight).orElse(0);
    }

    @PostMapping(value = "/incubator/place")
    public void placeEgg(@RequestParam UUID idTrainer, @RequestBody EggDTO egg) throws NotEnoughPlaceException {
        Optional<Incubator> optionalIncubator = incubatorRepository.findByIdTrainer(idTrainer);

        //TODO IF_EMPTY
        if(optionalIncubator.isPresent()){
            Incubator incubator = optionalIncubator.get();

            egg.setIncubationStartDate(Date.from(Instant.now()));
            Egg eggEntity = new Egg(egg, incubator);
            if(optionalIncubator.get().addEgg(eggEntity)) {
                //Egg added
                eggRepository.save(eggEntity);
                incubatorRepository.save(incubator);
            } else {
                //Not enough place (Weight > 3000)
                throw new NotEnoughPlaceException();
            }
        }
    }

    @PostMapping(value = "/incubator/hatch")
    public ResponseEntity<PokemonDTO> hatchEgg(@RequestParam UUID idTrainer, @RequestBody Egg egg) throws URISyntaxException {
        Optional<Incubator> optionalIncubator = incubatorRepository.findByIdTrainer(idTrainer);

        if(optionalIncubator.isPresent()) {
            Optional<Egg> optionalEggIncubated = optionalIncubator.get()
                    .getEggs()
                    .stream()
                    .filter(egg1 -> egg1.getId().equals(egg.getId()))
                    .findFirst();
            if(optionalEggIncubated.isPresent()) {
                Egg eggIncubated = optionalEggIncubated.get();

                if(eggIncubated.isIncubationFinished()){
                    RestTemplate restTemplate = new RestTemplate();
                    return restTemplate.getForEntity(
                            new URI("http://localhost:8083/pokemons/" + eggIncubated.getIdPokemon() + "/generate"),
                            PokemonDTO.class);
                }
            }
        }
        return ResponseEntity.noContent().build();
    }
}
