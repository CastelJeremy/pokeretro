package net.pokeretro.inventory.repository;

import net.pokeretro.inventory.model.Money;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MoneyRepository extends JpaRepository<Money, UUID> {
}
