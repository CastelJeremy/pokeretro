package net.pokeretro.inventory.repository;

import net.pokeretro.inventory.model.Egg;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EggRepository extends JpaRepository<Egg, UUID> {
    public Optional<Egg> findByIdAndTrainerId(UUID id, UUID trainerId);
    public List<Egg> findAllByTrainerId(UUID trainerId);
}
