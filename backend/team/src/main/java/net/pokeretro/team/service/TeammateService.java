package net.pokeretro.team.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import net.pokeretro.team.entity.Pokemon;
import net.pokeretro.team.entity.PokemonDTO;
import net.pokeretro.team.entity.Stat;
import net.pokeretro.team.entity.Teammate;
import net.pokeretro.team.repository.TeammateRepository;

@Service
public class TeammateService {
    @Autowired
    TeammateRepository teammateRepository;

    public Teammate insertPokemon(UUID trainerUuid, PokemonDTO pokemonDto) {
        List<Teammate> teammates = teammateRepository.findAllByTrainerUuid(trainerUuid);
        Integer position = teammates.size() + 1;

        if (position > 7)
            throw new ResponseStatusException(HttpStatus.CONFLICT);

        Pokemon pokemon = new Pokemon();
        pokemon.serial = pokemonDto.getId();
        pokemon.name = pokemonDto.getName();
        pokemon.types = pokemonDto.getTypes().stream().map(type -> type.getName()).collect(Collectors.toList());

        Stat stat = new Stat(
                pokemonDto.getHp(),
                pokemonDto.getAttack(),
                pokemonDto.getDefense(),
                pokemonDto.getSpecialAttack(),
                pokemonDto.getSpecialDefense(),
                pokemonDto.getSpeed());

        Teammate teammate = new Teammate(UUID.randomUUID());
        teammate.setTrainerUuid(trainerUuid);
        teammate.setPosition(position);
        ;
        teammate.setName(pokemon.name);
        teammate.setLevel(1);
        teammate.setXp(0L);
        teammate.setBaseStat(stat);
        teammate.setActiveStat(stat);
        teammate.setPokemon(pokemon);
        teammateRepository.save(teammate);

        return teammate;
    }

    public List<Teammate> swapTeammates(UUID trainerUuid, Teammate teammate1, Teammate teammate2) {
        Integer position = teammate1.getPosition();

        teammate1.setPosition(teammate2.getPosition());
        teammate2.setPosition(position);

        teammateRepository.save(teammate1);
        teammateRepository.save(teammate2);

        return teammateRepository.findAllByTrainerUuid(trainerUuid);
    }

    public List<Teammate> removeTeammate(UUID trainerUuid, Teammate teammate) {
        teammateRepository.delete(teammate);
        List<Teammate> teammates = teammateRepository.findAllByTrainerUuid(trainerUuid);

        teammates.stream().forEach(t -> {
            if (t.getPosition() > teammate.getPosition())
                t.setPosition(t.getPosition() - 1);
        });

        teammateRepository.saveAll(teammates);

        return teammates;
    }

    public List<Teammate> healAll(UUID trainerUuid) {
        List<Teammate> teammates = teammateRepository.findAllByTrainerUuid(trainerUuid);
        teammates.stream().forEach(teammate -> teammate.setActiveStat(teammate.getBaseStat()));

        teammateRepository.saveAll(teammates);

        return teammates;
    }
}
