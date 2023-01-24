package net.pokeretro.inventory.repository;

import net.pokeretro.inventory.model.Egg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EggRepository extends JpaRepository<Egg, Integer> {
}
