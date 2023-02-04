package net.pokeretro.battle.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import net.pokeretro.battle.entity.BattleState;

public interface BattleStateRepository extends CrudRepository<BattleState, UUID> {
}
