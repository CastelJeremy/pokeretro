package net.pokeretro.inventory.repository;

import net.pokeretro.inventory.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, UUID> {

    @Query("SELECT i.idTrainer, i.money, i.eggs FROM Inventory i where i.idTrainer = :idTrainer")
    Optional<Inventory> findById(UUID idTrainer);

    @Query("SELECT i.money FROM Inventory i LEFT JOIN Trainer t ON i.idTrainer = t.id WHERE i.idTrainer = :idTrainer")
    Integer getMoney(UUID idTrainer);
}
