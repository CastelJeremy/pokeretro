package net.pokeretro.pokemon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.pokeretro.pokemon.entity.Pokemon;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
}
