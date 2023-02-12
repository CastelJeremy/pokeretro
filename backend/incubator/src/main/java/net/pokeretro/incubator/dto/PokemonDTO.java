package net.pokeretro.incubator.dto;

import java.io.Serializable;
import java.util.List;

public class PokemonDTO implements Serializable {
    private Integer id;
    private String name;
    private List<String> types;
    private List<CapacityDTO> capacities;
    private StatDTO baseStat;
    private StatDTO individualStat;
    private Integer evolutionLevel;
    private Integer evolutionId;
    private Integer rarity;

    public PokemonDTO() {
    }

    public PokemonDTO(Integer id, String name, List<String> types, List<CapacityDTO> capacities, StatDTO baseStat,
            StatDTO individualStat, Integer evolutionLevel, Integer evolutionId, Integer rarity) {
        this.id = id;
        this.name = name;
        this.types = types;
        this.capacities = capacities;
        this.baseStat = baseStat;
        this.individualStat = individualStat;
        this.evolutionLevel = evolutionLevel;
        this.evolutionId = evolutionId;
        this.rarity = rarity;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getTypes() {
        return types;
    }

    public List<CapacityDTO> getCapacities() {
        return capacities;
    }

    public StatDTO getBaseStat() {
        return baseStat;
    }

    public StatDTO getIndividualStat() {
        return individualStat;
    }

    public Integer getEvolutionLevel() {
        return evolutionLevel;
    }

    public Integer getEvolutionId() {
        return evolutionId;
    }

    public Integer getRarity() {
        return rarity;
    }
}