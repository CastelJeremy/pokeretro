package net.pokeretro.shop.model;

import java.util.Collection;
import java.util.UUID;

public class CommunityShop extends Shop {
    public CommunityShop(Collection<Offer> offers) {
        super(UUID.fromString("2f250d17-b179-4073-9b28-09d10af6079c"), offers);
    }
}
