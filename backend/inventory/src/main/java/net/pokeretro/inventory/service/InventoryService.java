package net.pokeretro.inventory.service;

import net.pokeretro.inventory.exception.NotEnoughMoneyException;
import net.pokeretro.inventory.model.Inventory;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    public void addMoney(Inventory inventory, int amount) throws NotEnoughMoneyException {
        inventory.addMoney(amount);
    }
}
