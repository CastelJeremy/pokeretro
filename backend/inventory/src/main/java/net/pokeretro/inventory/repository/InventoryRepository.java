package net.pokeretro.inventory.repository;

import net.pokeretro.inventory.model.Egg;
import net.pokeretro.inventory.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, UUID> {

    /*@Query("SELECT i.idTrainer, i.money, i.eggs FROM Inventory i where i.idTrainer = :idTrainer")
    Optional<Inventory> findById(UUID idTrainer);*/

    @Query("SELECT i.money FROM Inventory i LEFT JOIN Trainer t ON i.idTrainer = t.id WHERE i.idTrainer = :idTrainer")
    Integer getMoney(UUID idTrainer);

    /*@Query("SELECT e.id, e.time, e.weight, e.idPokemon, e.inventory FROM Egg e WHERE e.inventory = :inventory")
    Collection<Egg> getEggsById(Inventory inventory);*/
}
