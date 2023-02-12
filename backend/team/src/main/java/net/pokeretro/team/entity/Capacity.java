package net.pokeretro.team.entity;

import net.pokeretro.team.dto.CapacityDTO;

public class Capacity {
    private String name;
    private String category;
    private Integer power;
    private Integer accuracy;
    private Integer pp;
    private String type;
    private Integer useCount;

    public Capacity() {
    }

    public Capacity(String name, String category, Integer power, Integer accuracy, Integer pp, String type,
            Integer useCount) {
        this.name = name;
        this.category = category;
        this.power = power;
        this.accuracy = accuracy;
        this.pp = pp;
        this.type = type;
        this.useCount = useCount;
    }

    public String getName() {
        return this.name;
    }

    public String getCategory() {
        return this.category;
    }

    public Integer getPower() {
        return this.power;
    }

    public Integer getAccuracy() {
        return this.accuracy;
    }

    public Integer getPp() {
        return this.pp;
    }

    public String getType() {
        return this.type;
    }

    public Integer getUseCount() {
        return useCount;
    }

    public void setUseCount(Integer useCount) {
        this.useCount = useCount;
    }

    public static Capacity fromDto(CapacityDTO capacityDTO) {
        return new Capacity(
                capacityDTO.getName(),
                capacityDTO.getCategory(),
                capacityDTO.getPower(),
                capacityDTO.getAccuracy(),
                capacityDTO.getPp(),
                capacityDTO.getType(),
                capacityDTO.getUseCount());
    }

    public CapacityDTO toDto() {
        return new CapacityDTO(name, category, power, accuracy, pp, type, useCount, null);
    }
}
