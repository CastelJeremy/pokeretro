package net.pokeretro.shop.controller;

import com.google.gson.JsonObject;
import net.pokeretro.shop.exception.CategoryNotFoundException;
import net.pokeretro.shop.exception.NotEnoughMoneyException;
import net.pokeretro.shop.model.*;
import net.pokeretro.shop.repositories.OfferRepository;
import net.pokeretro.shop.repositories.ShopRepository;
import net.pokeretro.shop.utils.RequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ShopController {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private OfferRepository offerRepository;

    @GetMapping(value = "/shop")
    public ResponseEntity<Shop> showShop(@RequestParam StringModel category) throws CategoryNotFoundException {
        if(category.getValue().equals("base")){
            Optional<Shop> shop = shopRepository.findById(UUID.fromString("0cdad3af-d414-4013-acf7-0c9c1195e9c3"));
            return shop.map(ResponseEntity::ok).orElse(null);
        } else if (category.getValue().equals("community")) {
            Optional<Shop> shop = shopRepository.findById(UUID.fromString("2f250d17-b179-4073-9b28-09d10af6079c"));
            return shop.map(ResponseEntity::ok).orElse(null);
        } else throw new CategoryNotFoundException();
    }

    @PostMapping(value = "/shop/buy")
    public void buyEgg(@RequestParam Trainer buyer, @RequestBody Offer offer)
            throws IOException, NotEnoughMoneyException {
        JsonObject moneyAmountResponse = RequestBuilder.query(new URL("http://localhost:8082/money?idTrainer=" + buyer.getId()), "GET", null);
        int money = moneyAmountResponse.get("amount").getAsInt();

        if(money >= offer.getPrice()) {
            RequestBuilder.query(new URL("http://localhost:8082/money/withdraw?idTrainer=" + buyer.getId()), "POST", null);
            RequestBuilder.query(new URL("http://localhost:8082/egg?idTrainer=" + buyer.getId()), "POST", null);
        } else {
            throw new NotEnoughMoneyException();
        }
    }

    @PostMapping(value = "/shop/sell")
    public void sellEgg(@RequestParam Trainer seller, @RequestParam Egg egg, @RequestParam int price)
            throws IOException {
        RequestBuilder.query(new URL("http://localhost:8082/egg?idTrainer=" + seller.getId()), "DELETE", null);
        RequestBuilder.query(new URL("http://localhost:8082/money/deposit?idTrainer=" + seller.getId()), "POST", null);

        offerRepository.save(new Offer(egg, price));
    }

    @GetMapping(value = "/shop/sell")
    public Collection<Offer> getEggInSell(@RequestParam Trainer trainer) {
        return offerRepository.findAllBySeller(trainer);
    }

    @PostMapping(value = "/shop/sell/cancel")
    public void cancelSell(@RequestParam Offer offer) {

    }

    @GetMapping(value = "/shop/history")
    public void getShopHistory(@RequestParam Trainer trainer) {

    }

    @PostMapping(value = "/shop/refresh")
    public void refreshShop() {

    }
}
