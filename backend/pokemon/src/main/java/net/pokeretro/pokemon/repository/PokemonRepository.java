package net.pokeretro.pokemon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.pokeretro.pokemon.entity.Pokemon;

public interface PokemonRepository extends JpaRepository<Pokemon, Integer> {
    @Query("SELECT p FROM Pokemon p WHERE p NOT IN (SELECT po.evolution FROM Pokemon po WHERE po.evolution IS NOT NULL)")
    public List<Pokemon> findAllEggable();
}
