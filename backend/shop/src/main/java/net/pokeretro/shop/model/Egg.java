package net.pokeretro.shop.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "eggs")
public class Egg {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID trainerId;
    private Integer time;
    private Integer weight;
    private Integer price;
    private Integer pokemonId;
    private Integer shopId; // 1 - base, 2 - community, 3 - external

    public Egg() {
    }

    public Egg(UUID id, Integer time, Integer weight, Integer pokemonId) {
        this.id = id;
        this.time = time;
        this.weight = weight;
        this.pokemonId = pokemonId;
    }

    public Egg(Integer time, Integer weight, Integer price, Integer pokemonId, Integer shopId) {
        this.time = time;
        this.weight = weight;
        this.price = price;
        this.pokemonId = pokemonId;
        this.shopId = shopId;
    }

    public UUID getTrainerId() {
        return trainerId;
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

    public Integer getPrice() {
        return price;
    }

    public Integer getPokemonId() {
        return pokemonId;
    }

    public Integer getShopId() {
        return shopId;
    }
}