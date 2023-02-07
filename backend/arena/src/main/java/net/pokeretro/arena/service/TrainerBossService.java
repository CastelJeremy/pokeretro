package net.pokeretro.arena.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import net.pokeretro.arena.dto.BattleStateDTO;
import net.pokeretro.arena.dto.PokemonDTO;
import net.pokeretro.arena.dto.TeammateDTO;
import net.pokeretro.arena.entity.Boss;
import net.pokeretro.arena.entity.TrainerBoss;
import net.pokeretro.arena.exception.AlreadyInBattleException;
import net.pokeretro.arena.exception.ChampionException;
import net.pokeretro.arena.exception.ImpossibleException;
import net.pokeretro.arena.exception.TrainerNotExistsException;
import net.pokeretro.arena.repository.BossRepository;
import net.pokeretro.arena.repository.TrainerBossRepository;

@Service
public class TrainerBossService {
    @Autowired
    BossRepository bossRepository;

    @Autowired
    TrainerBossRepository trainerBossRepository;

    public BattleStateDTO fightBoss(UUID trainerId) throws TrainerNotExistsException, AlreadyInBattleException, ImpossibleException, ChampionException {
        RestTemplate restTemplate = new RestTemplate();

        ParameterizedTypeReference<List<TeammateDTO>> getTeamResponseType = new ParameterizedTypeReference<List<TeammateDTO>>() {
        };

        // Get the trainer team
        ResponseEntity<List<TeammateDTO>> resTrainerTeam = restTemplate
                .exchange("http://localhost:8086/team/" + trainerId, HttpMethod.GET, null, getTeamResponseType);

        List<TeammateDTO> trainerTeam = resTrainerTeam.getBody();

        // Check trainer team exists
        if (trainerTeam.size() == 0) {
            throw new TrainerNotExistsException();
        }

        // Check if the trainer is not already in a battle
        try {
            ResponseEntity<BattleStateDTO> resExistBattle = restTemplate
                    .getForEntity("http://localhost:8088/battle/" + trainerId, BattleStateDTO.class);

            if (resExistBattle.getStatusCode().equals(HttpStatus.OK)) {
                throw new AlreadyInBattleException();
            }
        } catch (RestClientException e) {
            if (e.getRootCause() instanceof HttpClientErrorException) {
                if (!((HttpClientErrorException) e.getRootCause()).getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                    throw e;
                }
            }
        }

        // First time or did the trainer fight before
        Optional<TrainerBoss> optTrainerBoss = trainerBossRepository.findById(trainerId);
        TrainerBoss trainerBoss = null;

        if (optTrainerBoss.isEmpty()) {
            Optional<Boss> optBoss = bossRepository.findById(UUID.fromString("6ffe8eb1-642f-4e93-a827-0caff9af0825"));

            if (optBoss.isEmpty()) {
                throw new ImpossibleException();
            }

            trainerBoss = new TrainerBoss(trainerId, optBoss.get());
        } else {
            trainerBoss = optTrainerBoss.get();

            if (trainerBoss.getBoss() == null) {
                throw new ChampionException();
            }

            trainerBoss.setBoss(trainerBoss.getBoss().getNextBoss());
        }

        // Get the opponent team
        ResponseEntity<List<TeammateDTO>> resOpponentTeam = restTemplate
                .exchange("http://localhost:8086/team/" + trainerBoss.getBoss().getId(), HttpMethod.GET, null, getTeamResponseType);

        List<TeammateDTO> opponentTeam = resOpponentTeam.getBody();

        if (opponentTeam.size() == 0) {
            throw new ImpossibleException();
        }

        // Start the battle
        BattleStateDTO battleStateDTO = new BattleStateDTO(null, null, null, null, null, trainerTeam, opponentTeam);

        ResponseEntity<BattleStateDTO> resBattle = restTemplate
                .postForEntity("http://localhost:8088/battle/" + trainerId, battleStateDTO, BattleStateDTO.class);

        trainerBossRepository.save(trainerBoss);

        return resBattle.getBody();
    }

    public BattleStateDTO fightPokemon(UUID trainerId) throws AlreadyInBattleException, TrainerNotExistsException {
        RestTemplate restTemplate = new RestTemplate();

        ParameterizedTypeReference<List<TeammateDTO>> getTeamResponseType = new ParameterizedTypeReference<List<TeammateDTO>>() {
        };

        // Get the trainer team
        ResponseEntity<List<TeammateDTO>> resTrainerTeam = restTemplate
                .exchange("http://localhost:8086/team/" + trainerId, HttpMethod.GET, null, getTeamResponseType);

        List<TeammateDTO> trainerTeam = resTrainerTeam.getBody();

        if (trainerTeam.size() == 0) {
            throw new TrainerNotExistsException();
        }

        // Check if the trainer is not already in a battle
        try {
            ResponseEntity<BattleStateDTO> resExistBattle = restTemplate
                    .getForEntity("http://localhost:8088/battle/" + trainerId, BattleStateDTO.class);

            if (resExistBattle.getStatusCode().equals(HttpStatus.OK)) {
                throw new AlreadyInBattleException();
            }
        } catch (RestClientException e) {
            if (e.getRootCause() instanceof HttpClientErrorException) {
                if (!((HttpClientErrorException) e.getRootCause()).getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                    throw e;
                }
            }
        }

        // Generate a random size and ID for the opponent team
        Random rand = new Random();
        Integer opponentTeamSize = rand.nextInt(3) + 1;
        UUID opponentTeamId = UUID.randomUUID();

        // Generate pokemons and insert them into the opponent team
        for (int i = 0; i < opponentTeamSize; i++) {
            ResponseEntity<PokemonDTO> resPokemon = restTemplate
                    .getForEntity("http://localhost:8085/pokemons/generate", PokemonDTO.class);
            PokemonDTO pokemon = resPokemon.getBody();

            restTemplate.postForEntity("http://localhost:8086/team/" + opponentTeamId, pokemon, null);
        }

        // Get the opponent team
        ResponseEntity<List<TeammateDTO>> resOpponentTeam = restTemplate
                .exchange("http://localhost:8086/team/" + opponentTeamId, HttpMethod.GET, null, getTeamResponseType);

        List<TeammateDTO> opponentTeam = resOpponentTeam.getBody();

        // Start the battle
        BattleStateDTO battleStateDTO = new BattleStateDTO(null, null, null, null, null, trainerTeam, opponentTeam);

        ResponseEntity<BattleStateDTO> resBattle = restTemplate
                .postForEntity("http://localhost:8088/battle/" + trainerId, battleStateDTO, BattleStateDTO.class);

        return resBattle.getBody();
    }
}
