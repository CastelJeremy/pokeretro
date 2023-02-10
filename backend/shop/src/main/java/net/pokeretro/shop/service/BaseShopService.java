package net.pokeretro.shop.service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import net.pokeretro.shop.dto.MoneyDTO;
import net.pokeretro.shop.dto.PokemonDTO;
import net.pokeretro.shop.exception.BadRequestException;
import net.pokeretro.shop.model.Egg;
import net.pokeretro.shop.repositories.EggRepository;

@Service
public class BaseShopService {
    @Autowired
    EggRepository eggRepository;

    public Egg buy(UUID trainerId, Egg egg) throws BadRequestException {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<MoneyDTO> req = new HttpEntity<>(new MoneyDTO(egg.getPrice()));

        try {
            restTemplate.exchange("http://inventory-app:8080/money/withdraw/" + trainerId, HttpMethod.PUT, req,
                    MoneyDTO.class);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().is4xxClientError()) {
                System.out.println(e.getMessage());
                throw new BadRequestException();
            }

            throw e;
        }

        try {
            restTemplate.postForEntity("http://inventory-app:8080/egg/" + trainerId, egg, null);
        } catch (HttpClientErrorException e) {
            HttpEntity<MoneyDTO> fix = new HttpEntity<>(new MoneyDTO(egg.getPrice()));
            restTemplate.exchange("http://inventory-app:8080/money/withdraw/" + trainerId, HttpMethod.PUT, fix,
                    MoneyDTO.class);

            throw e;
        }

        return egg;
    }

    public Egg sell(UUID trainerId, Egg egg) throws BadRequestException {
        RestTemplate restTemplate = new RestTemplate();
        ParameterizedTypeReference<List<Egg>> eggsRes = new ParameterizedTypeReference<List<Egg>>() {
        };

        ResponseEntity<List<Egg>> eggs = restTemplate.exchange("http://inventory-app:8080/egg/" + trainerId, HttpMethod.GET,
                null, eggsRes);

        List<Egg> match = eggs.getBody().stream().filter((e) -> e.getId().equals(egg.getId())).toList();

        if (match.size() == 0)
            throw new BadRequestException();

        Integer price = match.get(0).getPrice();

        HttpEntity<Egg> delEgg = new HttpEntity<Egg>(egg);
        restTemplate.exchange("http://inventory-app:8080/egg/" + trainerId, HttpMethod.DELETE, delEgg, eggsRes);

        HttpEntity<MoneyDTO> req = new HttpEntity<>(new MoneyDTO(price));
        restTemplate.exchange("http://inventory-app:8080/money/deposit/" + trainerId,
                HttpMethod.PUT, req, MoneyDTO.class);

        return match.get(0);
    }

    @Transactional
    public List<Egg> refresh() {
        RestTemplate restTemplate = new RestTemplate();
        ParameterizedTypeReference<List<PokemonDTO>> pokemonResponseType = new ParameterizedTypeReference<List<PokemonDTO>>() {
        };
        ResponseEntity<List<PokemonDTO>> res = restTemplate.exchange("http://pokemon-app:8080/pokemons/eggable",
                HttpMethod.GET, null, pokemonResponseType);

        List<PokemonDTO> pokemons = res.getBody();
        Random rand = new Random();

        eggRepository.deleteAllByShopId(1);

        for (int i = 0; i < 6; i++) {
            PokemonDTO pokemon = pokemons.get(rand.nextInt(pokemons.size()));
            Egg egg = new Egg(rand.nextInt(10, 70), rand.nextInt(30, 400), pokemon.getRarity() * 10, pokemon.getId(), 1);

            eggRepository.save(egg);
        }

        return eggRepository.findAllByShopId(1);
    }
}
