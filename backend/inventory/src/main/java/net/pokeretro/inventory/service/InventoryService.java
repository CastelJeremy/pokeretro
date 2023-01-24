package net.pokeretro.inventory.service;

import net.pokeretro.inventory.exception.NotEnoughMoneyException;
import net.pokeretro.inventory.model.Egg;
import net.pokeretro.inventory.model.Inventory;
import net.pokeretro.inventory.repository.EggRepository;
import net.pokeretro.inventory.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private EggRepository eggRepository;

    public Inventory createInventory(UUID idTrainer) {
        return inventoryRepository.save(new Inventory(idTrainer));
    }

    public void addMoney(Inventory inventory, int amount) throws NotEnoughMoneyException {
        inventory.addMoney(amount);
    }

    public boolean addEgg(Inventory inventory, Egg egg) {
        egg.setInventory(inventory);
        eggRepository.save(egg);
        return inventory.getEggs().add(egg);
    }

    public boolean removeEgg(Inventory inventory, Egg egg) {
        egg.setInventory(null);
        eggRepository.save(egg);
        return inventory.getEggs().remove(egg);
    }
}
