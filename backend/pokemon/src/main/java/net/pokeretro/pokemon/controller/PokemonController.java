package net.pokeretro.pokemon.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import net.pokeretro.pokemon.entity.Pokemon;
import net.pokeretro.pokemon.repository.PokemonRepository;
import net.pokeretro.pokemon.service.PokemonService;

@RestController
public class PokemonController {
    @Autowired
    private PokemonRepository pokemonRepository;

    @Autowired
    private PokemonService pokemonService;

    @CrossOrigin
    @GetMapping("/pokemons")
    public ResponseEntity<List<Pokemon>> getPokemons() {
        return ResponseEntity.ok(pokemonRepository.findAll());
    }

    @CrossOrigin
    @GetMapping("/pokemons/{id}")
    public ResponseEntity<Pokemon> getPokemon(@PathVariable("id") Long id) {
        Optional<Pokemon> pokemon = pokemonRepository.findById(id);

        if (pokemon.isPresent())
            return ResponseEntity.ok(pokemon.get());

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @CrossOrigin
    @PostMapping("/pokemons/{id}/generate")
    public ResponseEntity<Pokemon> postPokemon(@PathVariable("id") Long id) {
        Optional<Pokemon> pokemon = pokemonRepository.findById(id);

        if (pokemon.isPresent()) {
            return ResponseEntity.ok(pokemonService.generate(pokemon.get()));
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
