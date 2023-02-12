package net.pokeretro.pokemon.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import net.pokeretro.pokemon.dto.PokemonDTO;
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
    public ResponseEntity<List<PokemonDTO>> getPokemons() {
        List<Pokemon> pokemons = pokemonRepository.findAll();
        List<PokemonDTO> pokemonDTOs = pokemons.stream().map(pokemon -> pokemon.toDto()).toList();

        return ResponseEntity.ok(pokemonDTOs);
    }

    @CrossOrigin
    @GetMapping("/pokemons/eggable")
    public ResponseEntity<List<PokemonDTO>> getPokemonsEggable() {
        List<Pokemon> pokemons = pokemonRepository.findAllEggable();
        List<PokemonDTO> pokemonDTOs = pokemons.stream().map(pokemon -> pokemon.toDto()).toList();

        return ResponseEntity.ok(pokemonDTOs);
    }

    @CrossOrigin
    @GetMapping("/pokemons/{id}")
    public ResponseEntity<PokemonDTO> getPokemon(@PathVariable Integer id) {
        Optional<Pokemon> pokemon = pokemonRepository.findById(id);

        if (pokemon.isPresent())
            return ResponseEntity.ok(pokemon.get().toDto());

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @CrossOrigin
    @GetMapping("/pokemons/generate")
    public ResponseEntity<PokemonDTO> generateRandomPokemon() {
        return ResponseEntity.ok(pokemonService.generateRandom().toDto());
    }

    @CrossOrigin
    @GetMapping("/pokemons/{id}/generate")
    public ResponseEntity<PokemonDTO> generatePokemon(@PathVariable Integer id) {
        Optional<Pokemon> pokemon = pokemonRepository.findById(id);

        if (pokemon.isPresent()) {
            return ResponseEntity.ok(pokemonService.generate(pokemon.get()).toDto());
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
