package net.pokeretro.shop.model;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "shop")
public abstract class Shop {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @OneToMany
    @JoinColumn(name = "id_shop")
    private Collection<Offer> offers;

    public Shop() {}

    public Shop(UUID id, Collection<Offer> offers) {
        this.id = id;
        this.offers = offers;
    }

    public UUID getId() {
        return id;
    }

    public Collection<Offer> getOffers() {
        return offers;
    }

    public void setOffers(Collection<Offer> offers) {
        this.offers = offers;
    }
}
