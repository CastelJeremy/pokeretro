package net.pokeretro.pokemon.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class PokemonCapacityId implements Serializable {
    @Column(name = "id_pokemon")
    private Integer pokemonId;

    @Column(name = "id_capacity")
    private Integer capacityId;
}
