package net.pokeretro.shop.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import net.pokeretro.shop.model.Egg;

public interface EggRepository extends CrudRepository<Egg, UUID> {
    public Optional<Egg> findByIdAndShopId(UUID id, Integer shopId);
    public List<Egg> findAllByShopId(Integer shopId);
    public void deleteAllByShopId(Integer shopId);
}
