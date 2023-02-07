package net.pokeretro.arena.dto;

import java.util.List;
import java.util.UUID;

public class TeammateDTO {
    private UUID id;
    private UUID trainerUuid;
    private Integer position;
    private String name;
    private Integer level;
    private Long xp;
    private StatDTO baseStat;
    private StatDTO individualStat;
    private StatDTO effortStat;
    private StatDTO stat;
    private StatDTO currentStat;
    private List<CapacityDTO> capacities;
    private PokemonDTO pokemon;

    public TeammateDTO() {
    }

    public TeammateDTO(UUID id, UUID trainerUuid, Integer position, String name, Integer level, Long xp,
            StatDTO baseStat, StatDTO individualStat, StatDTO effortStat, StatDTO stat, StatDTO currentStat,
            List<CapacityDTO> capacities, PokemonDTO pokemon) {
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
        return id;
    }

    public UUID getTrainerUuid() {
        return trainerUuid;
    }

    public Integer getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public Integer getLevel() {
        return level;
    }

    public Long getXp() {
        return xp;
    }

    public StatDTO getBaseStat() {
        return baseStat;
    }

    public StatDTO getIndividualStat() {
        return individualStat;
    }

    public StatDTO getEffortStat() {
        return effortStat;
    }

    public StatDTO getStat() {
        return stat;
    }

    public StatDTO getCurrentStat() {
        return currentStat;
    }

    public List<CapacityDTO> getCapacities() {
        return capacities;
    }

    public PokemonDTO getPokemon() {
        return pokemon;
    }
}
