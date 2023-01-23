package net.pokeretro.pokemon.service;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import net.pokeretro.pokemon.entity.Capacity;
import net.pokeretro.pokemon.entity.Pokemon;

@Service
public class PokemonService {
    public Pokemon generate(Pokemon pokemon) {
        List<Capacity> capacities = pokemon.getCapacities();
        Random random = new Random();

        capacities.remove(random.nextInt(capacities.size()));

        return pokemon;
    }
}
