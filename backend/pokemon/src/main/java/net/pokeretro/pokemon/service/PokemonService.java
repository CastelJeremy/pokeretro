package net.pokeretro.pokemon.service;

import java.util.Random;

import org.springframework.stereotype.Service;

import net.pokeretro.pokemon.entity.Pokemon;
import net.pokeretro.pokemon.entity.Stat;

@Service
public class PokemonService {
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
}
