package net.pokeretro.inventory.repository;

import net.pokeretro.inventory.model.Egg;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EggRepository extends JpaRepository<Egg, Integer> {
    public Optional<Egg> findByIdAndTrainerId(Integer id, UUID trainerId);
    public List<Egg> findAllByTrainerId(UUID trainerId);
}
