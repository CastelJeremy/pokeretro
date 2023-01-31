package net.pokeretro.incubator.respositories;

import net.pokeretro.incubator.model.Incubator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IncubatorRepository extends JpaRepository<Incubator, UUID> {
    Optional<Incubator> findByIdTrainer(UUID idTrainer);
}
