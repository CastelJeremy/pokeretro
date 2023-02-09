package net.pokeretro.incubator.service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import net.pokeretro.incubator.dto.PokemonDTO;
import net.pokeretro.incubator.exception.NotEnoughPlaceException;
import net.pokeretro.incubator.exception.NotReadyToHatchException;
import net.pokeretro.incubator.model.Egg;
import net.pokeretro.incubator.respositories.EggRepository;

@Service
public class IncubatorService {
    @Autowired
    EggRepository eggRepository;

    public List<Egg> place(UUID trainerId, Egg egg) throws NotEnoughPlaceException {
        List<Egg> eggs = eggRepository.findAllByTrainerId(trainerId);

        Integer weight = eggs.stream().mapToInt(e -> e.getWeight()).sum();

        if (weight + egg.getWeight() > 3000)
            throw new NotEnoughPlaceException();

        egg.setIncubationStartDate(Date.from(Instant.now()));
        egg.setTrainerId(trainerId);
        eggRepository.save(egg);

        return eggRepository.findAllByTrainerId(trainerId);
    }

    public List<Egg> hatch(UUID trainerId, Egg egg) throws NotReadyToHatchException {
        if (egg.isIncubationFinished()) {
            RestTemplate restTemplate = new RestTemplate();

            // Generate pokemon
            ResponseEntity<PokemonDTO> pokemon = restTemplate.getForEntity("http://pokemon-app:8080/pokemons/" + egg.getPokemonId() + "/generate", PokemonDTO.class);

            // Send pokemon to team
            HttpEntity<PokemonDTO> requesEntity = new HttpEntity<PokemonDTO>(pokemon.getBody());
            restTemplate.postForEntity("http://team-app:8080/team/" + trainerId, requesEntity, null);

            eggRepository.delete(egg);

            return eggRepository.findAllByTrainerId(trainerId);
        }

        throw new NotReadyToHatchException();
    }
}
