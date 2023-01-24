package net.pokeretro.inventory.utils;

import com.google.gson.JsonObject;

public interface JSONable<T> {
    JsonObject toJSON();
    T fromJSON(JsonObject jsonObject);
}
