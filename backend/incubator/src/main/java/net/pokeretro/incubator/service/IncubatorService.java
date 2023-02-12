package net.pokeretro.incubator.service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import net.pokeretro.incubator.dto.PokemonDTO;
import net.pokeretro.incubator.exception.InventoryRemovalException;
import net.pokeretro.incubator.exception.NotEnoughPlaceException;
import net.pokeretro.incubator.exception.NotReadyToHatchException;
import net.pokeretro.incubator.model.Egg;
import net.pokeretro.incubator.respositories.EggRepository;

@Service
public class IncubatorService {
    @Autowired
    EggRepository eggRepository;

    public List<Egg> place(UUID trainerId, Egg egg) throws NotEnoughPlaceException, InventoryRemovalException {
        List<Egg> eggs = eggRepository.findAllByTrainerId(trainerId);

        // Necessary for Date convertion to remove egg from inventory
        egg.setIncubationStartDate(Date.from(Instant.now()));

        // Sum total weight of incubators
        Integer weight = eggs.stream().mapToInt(e -> e.getWeight()).sum();

        // Check it does not exceed 3Kg
        if (weight + egg.getWeight() > 3000) {
            throw new NotEnoughPlaceException();
        }

        // Remove egg from inventory
        RestTemplate restTemplate = new RestTemplate();
        ParameterizedTypeReference<List<Egg>> eggsRes = new ParameterizedTypeReference<List<Egg>>() {
        };
        HttpEntity<Egg> reqEgg = new HttpEntity<Egg>(egg);
        ResponseEntity<List<Egg>> res = restTemplate.exchange("http://inventory-app:8080/egg/" + trainerId, HttpMethod.DELETE, reqEgg, eggsRes);

        if (!res.getStatusCode().equals(HttpStatus.OK)) {
            throw new InventoryRemovalException();
        }

        // Update egg informations and put it in the incubator
        egg.setTrainerId(trainerId);
        eggRepository.save(egg);

        return eggRepository.findAllByTrainerId(trainerId);
    }

    public List<Egg> hatch(UUID trainerId, Egg egg) throws NotReadyToHatchException {
        if (egg.isIncubationFinished()) {
            RestTemplate restTemplate = new RestTemplate();

            // Generate a pokemon
            ResponseEntity<PokemonDTO> pokemon = restTemplate.getForEntity(
                    "http://pokemon-app:8080/pokemons/" + egg.getPokemonId() + "/generate", PokemonDTO.class);

            // Send pokemon to team
            HttpEntity<PokemonDTO> requesEntity = new HttpEntity<PokemonDTO>(pokemon.getBody());
            restTemplate.postForEntity("http://team-app:8080/team/" + trainerId, requesEntity, null);

            // Remove the egg from the incubator
            eggRepository.delete(egg);

            return eggRepository.findAllByTrainerId(trainerId);
        }

        throw new NotReadyToHatchException();
    }
}
