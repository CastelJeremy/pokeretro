package net.pokeretro.inventory.controller;

import net.pokeretro.inventory.exception.NotEnoughMoneyException;
import net.pokeretro.inventory.model.Egg;
import net.pokeretro.inventory.model.Money;
import net.pokeretro.inventory.repository.EggRepository;
import net.pokeretro.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private EggRepository eggRepository;

    @GetMapping("/money/{trainerId}")
    public ResponseEntity<Money> getMoney(@PathVariable UUID trainerId) {
        Money money = inventoryService.getMoney(trainerId);

        return ResponseEntity.ok(money);
    }

    @PutMapping("/money/deposit/{trainerId}")
    public ResponseEntity<Money> addMoney(@PathVariable UUID trainerId, @RequestBody Money money) {
        return ResponseEntity.ok(inventoryService.addMoney(trainerId, money));
    }

    @PutMapping("/money/withdraw/{trainerId}")
    public ResponseEntity<Money> removeMoney(@PathVariable UUID trainerId, @RequestBody Money money)
            throws NotEnoughMoneyException {
        return ResponseEntity.ok(inventoryService.removeMoney(trainerId, money));
    }

    @GetMapping("/egg/{trainerId}")
    public ResponseEntity<List<Egg>> getEggs(@PathVariable UUID trainerId) {
        return ResponseEntity.ok(eggRepository.findAllByTrainerId(trainerId));
    }

    @PostMapping("/egg/{trainerId}")
    public ResponseEntity<List<Egg>> addEgg(@PathVariable UUID trainerId, @RequestBody Egg egg) {
        inventoryService.addEgg(trainerId, egg);

        return ResponseEntity.ok(eggRepository.findAllByTrainerId(trainerId));
    }

    @DeleteMapping("/egg/{trainerId}")
    public ResponseEntity<List<Egg>> removeEgg(@PathVariable UUID trainerId, @RequestBody Egg egg) {
        Optional<Egg> optEgg = eggRepository.findByIdAndTrainerId(egg.getId(), trainerId);

        if (optEgg.isPresent()) {
            inventoryService.removeEgg(egg);

            return ResponseEntity.ok(eggRepository.findAllByTrainerId(trainerId));
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
