package net.pokeretro.incubator.respositories;

import net.pokeretro.incubator.model.Egg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EggRepository extends JpaRepository<Egg, UUID> {
    public Optional<Egg> findByIdAndTrainerId(UUID id, UUID trainerId);
    public List<Egg> findAllByTrainerId(UUID trainerId);
}
