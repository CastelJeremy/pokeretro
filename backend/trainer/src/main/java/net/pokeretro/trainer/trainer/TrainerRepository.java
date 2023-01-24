package net.pokeretro.trainer.trainer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, UUID> {
    @Query("SELECT t.id FROM Trainer t WHERE t.username=:username")
    Optional<Trainer> findIdByUsername(@Param("username") String username);

    @Query("SELECT t.id, t.username, t.gender, t.starter FROM Trainer t")
    Collection<Trainer> getUsers();
}

