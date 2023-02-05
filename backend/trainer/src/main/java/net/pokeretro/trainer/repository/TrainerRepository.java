package net.pokeretro.trainer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.pokeretro.trainer.entity.Trainer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TrainerRepository extends JpaRepository<Trainer, UUID> {
    public Optional<Trainer> findByUserIdAndName(UUID userId, String name);

    public List<Trainer> findAllByUserId(UUID userId);
}
