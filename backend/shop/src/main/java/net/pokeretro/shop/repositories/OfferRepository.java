package net.pokeretro.shop.repositories;

import net.pokeretro.shop.model.Offer;
import net.pokeretro.shop.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.UUID;

public interface OfferRepository extends JpaRepository<Offer, UUID> {
    Collection<Offer> findAllBySeller(Trainer seller);
}
