package net.pokeretro.incubator.respositories;

import net.pokeretro.incubator.model.Egg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EggRepository extends JpaRepository<Egg, UUID> {
}
