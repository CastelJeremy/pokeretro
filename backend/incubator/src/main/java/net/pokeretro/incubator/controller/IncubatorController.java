package net.pokeretro.incubator.controller;

import net.pokeretro.incubator.model.Egg;
import net.pokeretro.incubator.model.Incubator;
import net.pokeretro.incubator.model.PokemonDTO;
import net.pokeretro.incubator.respositories.IncubatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.UUID;

@RestController
public class IncubatorController {

    @Autowired
    private IncubatorRepository incubatorRepository;

    @GetMapping(value = "/incubator")
    public ResponseEntity<Incubator> getIncubator(@RequestParam UUID idTrainer) {
        Optional<Incubator> optionalIncubator = incubatorRepository.findByIdTrainer(idTrainer);
        return optionalIncubator.map(ResponseEntity::ok).orElse(null);
    }

    @GetMapping(value = "/incubator/weight")
    public Integer getWeight(@RequestParam UUID idTrainer) {
        Optional<Incubator> optionalIncubator = incubatorRepository.findByIdTrainer(idTrainer);
        return optionalIncubator.map(Incubator::getWeight).orElse(0);
    }

    @PostMapping(value = "/incubator/place")
    public void placeEgg(@RequestParam UUID idTrainer, @RequestBody Egg egg) {
        Optional<Incubator> optionalIncubator = incubatorRepository.findByIdTrainer(idTrainer);

        if(optionalIncubator.isPresent()){
            optionalIncubator.get().addEgg(egg);
            incubatorRepository.save(optionalIncubator.get());
        }
    }

    @PostMapping(value = "/incubator/hatch")
    public ResponseEntity<PokemonDTO> hatchEgg(@RequestParam UUID idTrainer, @RequestBody Egg egg) throws URISyntaxException {
        Optional<Incubator> optionalIncubator = incubatorRepository.findByIdTrainer(idTrainer);

        if(optionalIncubator.isPresent() && egg.isIncubationFinished()){
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.getForEntity(
                    new URI("http://localhost:8083/pokemons/" + egg.getIdPokemon() + "/generate"),
                    PokemonDTO.class);
        }
        return null;
    }
}
