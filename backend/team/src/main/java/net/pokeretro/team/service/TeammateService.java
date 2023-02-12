package net.pokeretro.team.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import net.pokeretro.team.dto.PokemonDTO;
import net.pokeretro.team.entity.Capacity;
import net.pokeretro.team.entity.Pokemon;
import net.pokeretro.team.entity.Stat;
import net.pokeretro.team.entity.Teammate;
import net.pokeretro.team.repository.TeammateRepository;

@Service
public class TeammateService {
    @Autowired
    TeammateRepository teammateRepository;

    @Autowired
    StatService statService;

    public Teammate addPokemon(UUID trainerUuid, PokemonDTO pokemonDto) {
        List<Teammate> teammates = teammateRepository.findAllByTrainerUuid(trainerUuid);
        Integer position = teammates.size() + 1;

        if (position > 6) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        Pokemon pokemon = Pokemon.fromDto(pokemonDto);
        Stat baseStat = Stat.fromDto(pokemonDto.getBaseStat());
        Stat individualStat = Stat.fromDto(pokemonDto.getIndividualStat());
        Stat effortStat = new Stat(0, 0, 0, 0, 0);
        Stat stat = statService.processStat(1, baseStat, individualStat, effortStat);
        List<Capacity> capacities = pokemonDto.getCapacities().stream().map(capacity -> Capacity.fromDto(capacity)).toList();

        capacities.stream().forEach(c -> c.setUseCount(0));

        Teammate teammate = new Teammate(
                UUID.randomUUID(),
                trainerUuid,
                position,
                pokemon.getName(),
                1,
                0L,
                baseStat,
                individualStat,
                effortStat,
                stat,
                stat,
                capacities,
                pokemon);

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
            if (t.getPosition() > teammate.getPosition()) {
                t.setPosition(t.getPosition() - 1);
            }
        });

        teammateRepository.saveAll(teammates);

        return teammates;
    }

    public List<Teammate> healAll(UUID trainerUuid) {
        List<Teammate> teammates = teammateRepository.findAllByTrainerUuid(trainerUuid);
        teammates.stream().forEach(teammate -> teammate.setCurrentStat(teammate.getStat()));

        teammateRepository.saveAll(teammates);

        return teammates;
    }
}
