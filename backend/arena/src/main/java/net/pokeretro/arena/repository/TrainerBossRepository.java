package net.pokeretro.arena.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import net.pokeretro.arena.entity.TrainerBoss;

public interface TrainerBossRepository extends JpaRepository<TrainerBoss, UUID> {

}
