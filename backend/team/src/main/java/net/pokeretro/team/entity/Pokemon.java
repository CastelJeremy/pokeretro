package net.pokeretro.team.entity;

import java.util.List;

import net.pokeretro.team.dto.PokemonDTO;

public class Pokemon {
    private Integer id;
    private String name;
    private List<String> types;

    public Pokemon() {
    }

    public Pokemon(Integer id, String name, List<String> types) {
        this.id = id;
        this.name = name;
        this.types = types;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getTypes() {
        return types;
    }

    public static Pokemon fromDto(PokemonDTO pokemonDTO) {
        return new Pokemon(pokemonDTO.getId(), pokemonDTO.getName(), pokemonDTO.getTypes());
    }

    public PokemonDTO toDto() {
        return new PokemonDTO(id, name, types, null, null, null, null, null, null);
    }
}
