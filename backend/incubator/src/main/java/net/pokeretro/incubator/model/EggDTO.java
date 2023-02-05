package net.pokeretro.incubator.model;

import java.util.Date;
import java.util.UUID;

public class EggDTO {
    private UUID id;
    private Integer time;
    private Date incubationStartDate;
    private Integer weight;
    private Integer idPokemon;

    public EggDTO(UUID id, Integer time, Date incubationStartDate, Integer weight, Integer idPokemon) {
        this.id = id;
        this.time = time;
        this.incubationStartDate = incubationStartDate;
        this.weight = weight;
        this.idPokemon = idPokemon;
    }

    public UUID getId() {
        return id;
    }

    public Integer getTime() {
        return time;
    }

    public Date getIncubationStartDate() {
        return incubationStartDate;
    }

    public void setIncubationStartDate(Date incubationStartDate) {
        this.incubationStartDate = incubationStartDate;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getIdPokemon() {
        return idPokemon;
    }
}
