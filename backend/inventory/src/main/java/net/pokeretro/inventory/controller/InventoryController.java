package net.pokeretro.inventory.controller;

import net.pokeretro.inventory.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryController {

    @Autowired
    private InventoryRepository inventoryRepository;

    @GetMapping
    public String getMoney(){
        return "jsonobject {amount:int}"; //TODO mettre le ID_TRAINER dans le token
    }
}
