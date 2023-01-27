package net.pokeretro.shop.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "offers")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "id_egg", unique = true)
    private Egg egg;

    @OneToOne
    @JoinColumn(name = "id_seller")
    private Trainer seller;

    @Column(name = "price", nullable = false)
    private Integer price;

    public Offer() {

    }

    public Offer(Egg egg, Integer price) {
        this.egg = egg;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public Egg getEgg() {
        return egg;
    }

    public Trainer getSeller() {
        return seller;
    }

    public Integer getPrice() {
        return price;
    }
}
