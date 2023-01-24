package net.pokeretro.pokemon.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "pokemon_capacity")
public class PokemonCapacity {
    @EmbeddedId
    private PokemonCapacityId pokemonCapacityId;

    @ManyToOne
    @MapsId("pokemonId")
    @JoinColumn(name = "id_pokemon")
    private Pokemon pokemon;

    @ManyToOne
    @MapsId("capacityId")
    @JoinColumn(name = "id_capacity")
    private Capacity capacity;

    @Column(nullable = false)
    private Integer level;

    public Capacity getCapacity() {
        return capacity;
    }

    public Integer getLevel() {
        return this.level;
    }
}
