package net.pokeretro.inventory.controller;

import com.google.gson.JsonObject;
import net.pokeretro.inventory.exception.NotEnoughMoneyException;
import net.pokeretro.inventory.model.Egg;
import net.pokeretro.inventory.model.Inventory;
import net.pokeretro.inventory.model.Money;
import net.pokeretro.inventory.repository.EggRepository;
import net.pokeretro.inventory.repository.InventoryRepository;
import net.pokeretro.inventory.service.InventoryService;
import net.pokeretro.inventory.utils.JSONUtils;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@RestController
public class InventoryController {

    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private EggRepository eggRepository;

    @GetMapping(value="/money")
    public String getMoney(@RequestParam UUID idTrainer) {
        Optional<Inventory> tempInv = inventoryRepository.findById(idTrainer);
        Inventory inv;
        // Si l'inventaire n'existe pas, on le crée
        if(tempInv.isEmpty())
            inv = inventoryService.createInventory(idTrainer);
        else
            inv = tempInv.get();

        return JSONUtils.intToJSON("amount", inv.getMoney()).toString();
    }

    @PostMapping(value="/money/deposit")
    public void addMoney(@RequestParam UUID idTrainer, @RequestBody Money money)
            throws NotEnoughMoneyException {
        Optional<Inventory> tempInv = inventoryRepository.findById(idTrainer);
        Inventory inv;
        // Si l'inventaire n'existe pas, on le crée
        if(tempInv.isEmpty())
            inv = inventoryService.createInventory(idTrainer);
        else
            inv = tempInv.get();

        inventoryService.addMoney(inv, money.getAmount());
        inventoryRepository.save(inv);
    }

    @PostMapping(value="/money/withdraw")
    public void removeMoney(@RequestParam UUID idTrainer, @RequestBody Money money)
            throws NotEnoughMoneyException {
        Optional<Inventory> tempInv = inventoryRepository.findById(idTrainer);
        Inventory inv;
        // Si l'inventaire n'existe pas, on le crée
        if(tempInv.isEmpty())
            inv = inventoryService.createInventory(idTrainer);
        else
            inv = tempInv.get();

        inventoryService.addMoney(inv, -money.getAmount());
        inventoryRepository.save(inv);
    }

    @GetMapping(value="/egg")
    public String getEggs(@RequestParam UUID idTrainer) {
        Optional<Inventory> tempInv = inventoryRepository.findById(idTrainer);
        Inventory inv;
        // Si l'inventaire n'existe pas, on le crée
        if(tempInv.isEmpty())
            inv = inventoryService.createInventory(idTrainer);
        else
            inv = tempInv.get();

        Collection<Egg> eggs = inv.getEggs();
        return JSONUtils.collectionToJSON("eggs", eggs).toString();
    }

    @PostMapping(value="/egg")
    public void addEgg(@RequestParam UUID idTrainer, @RequestBody Egg egg) {
        Optional<Inventory> tempInv = inventoryRepository.findById(idTrainer);
        Inventory inv;
        // Si l'inventaire n'existe pas, on le crée
        if(tempInv.isEmpty())
            inv = inventoryService.createInventory(idTrainer);
        else
            inv = tempInv.get();

        inventoryService.addEgg(inv, egg);
        inventoryRepository.save(inv);
    }

    @DeleteMapping(value="/egg")
    public void removeEgg(@RequestParam UUID idTrainer, @RequestBody Egg egg) {
        Optional<Inventory> tempInv = inventoryRepository.findById(idTrainer);
        Inventory inv;
        // Si l'inventaire n'existe pas, on le crée
        if(tempInv.isEmpty())
            inv = inventoryService.createInventory(idTrainer);
        else
            inv = tempInv.get();

        inventoryService.removeEgg(inv, egg);
        inventoryRepository.save(inv);
    }

    @GetMapping(value = "/report")
    public String generateReport(@RequestParam UUID idTrainer) {
        //TODO
        throw new NotYetImplementedException();
    }
}
