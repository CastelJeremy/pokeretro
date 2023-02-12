package net.pokeretro.shop.controller;

import net.pokeretro.shop.exception.BadRequestException;
import net.pokeretro.shop.model.*;
import net.pokeretro.shop.repositories.EggRepository;
import net.pokeretro.shop.service.BaseShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
public class ShopController {

    @Autowired
    private EggRepository eggRepository;

    @Autowired
    private BaseShopService baseShopService;

    @GetMapping("/shop/base")
    public ResponseEntity<List<Egg>> getBaseList() {
        return ResponseEntity.ok(eggRepository.findAllByShopId(1));
    }

    @PostMapping("/shop/base/buy/{trainerId}")
    public ResponseEntity<Egg> buyFromBase(@PathVariable UUID trainerId, @RequestBody Egg egg) {
        Optional<Egg> optEgg = eggRepository.findByIdAndShopId(egg.getId(), 1);

        if (optEgg.isPresent()) {
            try {
                return ResponseEntity.ok(baseShopService.buy(trainerId, optEgg.get()));
            } catch (BadRequestException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/shop/base/sell/{trainerId}")
    public ResponseEntity<Egg> sellToBase(@PathVariable UUID trainerId, @RequestBody Egg egg) {
        try {
            return ResponseEntity.ok(baseShopService.sell(trainerId, egg));
        } catch (BadRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/shop/base/refresh")
    public ResponseEntity<List<Egg>> refreshBase() {
        return ResponseEntity.ok(baseShopService.refresh());
    }
}
