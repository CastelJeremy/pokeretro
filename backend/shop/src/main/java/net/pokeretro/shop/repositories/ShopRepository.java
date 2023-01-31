package net.pokeretro.shop.repositories;

import net.pokeretro.shop.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShopRepository extends JpaRepository<Shop, UUID> {
}
