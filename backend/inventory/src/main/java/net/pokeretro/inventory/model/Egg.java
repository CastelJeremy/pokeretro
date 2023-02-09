package net.pokeretro.inventory.model;

import java.util.UUID;
import jakarta.persistence.*;

@Entity
@Table(name = "eggs")
public class Egg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_trainer")
    private UUID trainerId;

    @Column(name = "time", nullable = false)
    private Integer time;

    @Column(name = "weight", nullable = false)
    private Integer weight;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "id_pokemon", nullable = false)
    private Integer pokemonId;

    public Integer getId() {
        return id;
    }

    public void setTrainerId(UUID trainerId) {
        this.trainerId = trainerId;
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
}
