package net.pokeretro.arena.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import net.pokeretro.arena.entity.Boss;

public interface BossRepository extends JpaRepository<Boss, UUID> {
    
}
