package net.pokeretro.trainer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import net.pokeretro.trainer.dto.PokemonDTO;
import net.pokeretro.trainer.entity.Trainer;
import net.pokeretro.trainer.exception.TrainerCreateException;
import net.pokeretro.trainer.repository.TrainerRepository;

@Service
public class TrainerService {
    @Autowired
    private TrainerRepository trainerRepository;

    public Trainer create(Trainer trainer) throws TrainerCreateException {
        if (trainer.getGender().equals("boy") || trainer.getGender().equals("girl")) {

            Integer starter = trainer.getStarter();
            if (starter.equals(1) || starter.equals(4) || starter.equals(7)) {

                Optional<Trainer> optTrainer = trainerRepository.findByUserIdAndName(trainer.getUserId(),
                        trainer.getName());
                if (optTrainer.isEmpty()) {
                    trainerRepository.save(trainer);

                    // Generate a Pokemon
                    RestTemplate restTemplate = new RestTemplate();
                    ResponseEntity<PokemonDTO> resPokemon = restTemplate.getForEntity("http://pokemon-app:8080/pokemons/" + starter + "/generate", PokemonDTO.class);

                    // Insert Pokemon into team
                    restTemplate.postForEntity("http://team-app:8080/team/" + trainer.getId(), resPokemon.getBody(), null);

                    return trainer;
                }
            }
        }

        throw new TrainerCreateException();
    }
}
