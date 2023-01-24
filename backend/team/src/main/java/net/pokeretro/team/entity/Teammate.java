package net.pokeretro.team.entity;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("teammates")
public class Teammate {
    @Id
    private UUID id;

    private UUID trainerUuid;
    private Integer position;
    private String name;
    private Integer level;
    private Long xp;
    private Stat baseStat;
    private Stat activeStat;
    private Pokemon pokemon;

    public Teammate() {
    }

    public Teammate(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return this.id;
    }

    public UUID getTrainerUuid() {
        return this.trainerUuid;
    }

    public void setTrainerUuid(UUID trainerUuid) {
        this.trainerUuid = trainerUuid;
    }

    public Integer getPosition() {
        return this.position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return this.level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Long getXp() {
        return this.xp;
    }

    public void setXp(Long xp) {
        this.xp = xp;
    }

    public Stat getBaseStat() {
        return this.baseStat;
    }

    public void setBaseStat(Stat baseStat) {
        this.baseStat = baseStat;
    }

    public Stat getActiveStat() {
        return this.activeStat;
    }

    public void setActiveStat(Stat activeStat) {
        this.activeStat = activeStat;
    }

    public Pokemon getPokemon() {
        return this.pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }
}
