package net.pokeretro.inventory.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.pokeretro.inventory.model.Egg;

import java.util.Collection;
import java.util.Set;

public class JSONUtils {

    public static JsonObject intToJSON(String propertyName, int value) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(propertyName, value);
        return jsonObject;
    }

    public static JsonObject stringToJSON(String propertyName, String value) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(propertyName, value);
        return jsonObject;
    }

    public static JsonObject collectionToJSON(String propertyName, Collection<Egg> collection) {
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        for(JSONable<?> object : collection) {
            jsonArray.add(object.toJSON());
        }
        jsonObject.add(propertyName, jsonArray);
        return jsonObject;
    }
}
