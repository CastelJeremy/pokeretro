package net.pokeretro.shop.model;

import java.util.Collection;
import java.util.UUID;

public class BaseShop extends Shop {

    public BaseShop(Collection<Offer> offers) {
        super(UUID.fromString("0cdad3af-d414-4013-acf7-0c9c1195e9c3"), offers);
    }
}
