package net.pokeretro.team.entity;

import java.util.List;
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
    private Stat individualStat;
    private Stat effortStat;
    private Stat stat;
    private Stat currentStat;
    private List<Capacity> capacities;
    private Pokemon pokemon;

    public Teammate() {
    }

    public Teammate(UUID id, UUID trainerUuid, Integer position, String name, Integer level, Long xp, Stat baseStat, Stat individualStat, Stat effortStat,Stat stat, Stat currentStat, List<Capacity> capacities, Pokemon pokemon) {
        this.id = id;
        this.trainerUuid = trainerUuid;
        this.position = position;
        this.name = name;
        this.level = level;
        this.xp = xp;
        this.baseStat = baseStat;
        this.individualStat = individualStat;
        this.effortStat = effortStat;
        this.stat = stat;
        this.currentStat = currentStat;
        this.capacities = capacities;
        this.pokemon = pokemon;
    }

    public UUID getId() {
        return this.id;
    }

    public UUID getTrainerUuid() {
        return this.trainerUuid;
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

    public Stat getIndividualStat() {
        return individualStat;
    }

    public Stat getEffortStat() {
        return effortStat;
    }

    public Stat getStat() {
        return stat;
    }

    public Stat getCurrentStat() {
        return currentStat;
    }

    public void setCurrentStat(Stat currentStat) {
        this.currentStat = currentStat;
    }

    public List<Capacity> getCapacities() {
        return capacities;
    }

    public Pokemon getPokemon() {
        return this.pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }
}
