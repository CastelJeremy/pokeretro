package net.pokeretro.pokemon.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.pokeretro.pokemon.entity.Pokemon;
import net.pokeretro.pokemon.entity.Stat;
import net.pokeretro.pokemon.repository.PokemonRepository;

@Service
public class PokemonService {
    @Autowired
    PokemonRepository pokemonRepository;

    public Pokemon generate(Pokemon pokemon) {
        Random rand = new Random();

        Integer attack = rand.nextInt(16);
        Integer defense = rand.nextInt(16);
        Integer speed = rand.nextInt(16);
        Integer special = rand.nextInt(16);
        Integer hp = (attack % 2 * 8) + (defense % 2 * 4) + (speed % 2 * 2) + (special % 2 * 1);

        Stat individualStat = new Stat(
                null,
                hp,
                attack,
                defense,
                speed,
                special);

        // Handle capacities for level 1
        pokemon.getCapacities().removeIf(capacity -> capacity.getLevel() > 1);

        pokemon.setIndividualStat(individualStat);

        return pokemon;
    }

    public Pokemon generateRandom() {
        List<Pokemon> pokemons = pokemonRepository.findAll();
        Random rand = new Random();

        Pokemon pokemon = pokemons.get(rand.nextInt(pokemons.size()));

        return generate(pokemon);
    }
}
