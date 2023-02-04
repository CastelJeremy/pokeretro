package net.pokeretro.battle.entity;

import java.util.List;
import java.util.UUID;

import net.pokeretro.battle.dto.TeammateDTO;

public class Teammate {
    private UUID id;
    private String name;
    private Integer level;
    private Long xp;
    private Stat baseStat;
    private Stat stat;
    private Stat currentStat;
    private List<Capacity> capacities;
    private Pokemon pokemon;

    public Teammate() {
    }

    public Teammate(UUID id, String name, Integer level, Long xp, Stat baseStat, Stat stat, Stat currentStat,
            List<Capacity> capacities, Pokemon pokemon) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.xp = xp;
        this.baseStat = baseStat;
        this.stat = stat;
        this.currentStat = currentStat;
        this.capacities = capacities;
        this.pokemon = pokemon;
    }

    public UUID getId() {
        return id;
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

    public Stat getBaseStat() {
        return baseStat;
    }

    public Stat getStat() {
        return stat;
    }

    public Stat getCurrentStat() {
        return currentStat;
    }

    public List<Capacity> getCapacities() {
        return capacities;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public static Teammate fromDto(TeammateDTO teammateDTO) {
        return new Teammate(
                teammateDTO.getId(),
                teammateDTO.getName(),
                teammateDTO.getLevel(),
                teammateDTO.getXp(),
                Stat.fromDto(teammateDTO.getBaseStat()),
                Stat.fromDto(teammateDTO.getStat()),
                Stat.fromDto(teammateDTO.getCurrentStat()),
                teammateDTO.getCapacities().stream().map(capacity -> Capacity.fromDto(capacity)).toList(),
                Pokemon.fromDto(teammateDTO.getPokemon()));
    }

    public TeammateDTO toDto() {
        return new TeammateDTO(
                id,
                null,
                null,
                name,
                level,
                xp,
                baseStat.toDto(),
                null,
                null,
                stat.toDto(),
                currentStat.toDto(),
                capacities.stream().map(c -> c.toDto()).toList(),
                pokemon.toDto());
    }
}
