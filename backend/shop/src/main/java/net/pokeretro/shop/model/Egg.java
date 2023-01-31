package net.pokeretro.shop.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "eggs")
public class Egg {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "time")
    private Integer time;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "id_pokemon")
    private Integer idPokemon;

    public Egg(UUID id, Integer time, Integer weight, Integer idPokemon) {
        this.id = id;
        this.time = time;
        this.weight = weight;
        this.idPokemon = idPokemon;
    }

    public Egg() {

    }

    public UUID getId() {
        return id;
    }

    public Integer getTime() {
        return time;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getIdPokemon() {
        return idPokemon;
    }
}