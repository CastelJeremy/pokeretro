package net.pokeretro.team.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import net.pokeretro.team.entity.Teammate;

public interface TeammateRepository extends MongoRepository<Teammate, UUID> {
    public List<Teammate> findAllByTrainerUuid(UUID trainerUuid);
}
