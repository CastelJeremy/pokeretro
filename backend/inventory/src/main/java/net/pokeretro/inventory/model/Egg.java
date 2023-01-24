package net.pokeretro.inventory.model;

import com.google.gson.JsonObject;
import jakarta.persistence.*;
import net.pokeretro.inventory.utils.JSONable;

import java.util.UUID;

@Entity
@Table(name = "eggs")
public class Egg implements JSONable<Egg> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "time")
    private Integer time;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "id_pokemon")
    private Integer idPokemon;

    @ManyToOne
    @JoinColumn(name = "id_trainer")
    private Inventory inventory;

    public Egg(Integer id, Integer time, Integer weight, Integer idPokemon) {
        this.id = id;
        this.time = time;
        this.weight = weight;
        this.idPokemon = idPokemon;
    }

    public Egg() {

    }

    public Integer getId() {
        return id;
    }

    public Integer getTime() {
        return time;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getIdPokemon() {
        return idPokemon;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public JsonObject toJSON(){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("time", time);
        jsonObject.addProperty("weight", weight);
        jsonObject.addProperty("idPokemon", idPokemon);

        return jsonObject;
    }

    @Override
    public Egg fromJSON(JsonObject jsonObject){
        return new Egg(jsonObject.get("id").getAsInt(),
                jsonObject.get("time").getAsInt(),
                jsonObject.get("weight").getAsInt(),
                jsonObject.get("idPokemon").getAsInt());
    }
}
