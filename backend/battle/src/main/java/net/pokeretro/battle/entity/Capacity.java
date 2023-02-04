package net.pokeretro.battle.entity;

import net.pokeretro.battle.dto.CapacityDTO;

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
        return name;
    }

    public String getCategory() {
        return category;
    }

    public Integer getPower() {
        return power;
    }

    public Integer getAccuracy() {
        return accuracy;
    }

    public Integer getPp() {
        return pp;
    }

    public String getType() {
        return type;
    }

    public Integer getUseCount() {
        return useCount;
    }

    public void setUseCount(Integer useCount) {
        this.useCount = useCount;
    }

    public static Capacity fromDto(CapacityDTO capacityDto) {
        return new Capacity(capacityDto.getName(), capacityDto.getCategory(), capacityDto.getPower(),
                capacityDto.getAccuracy(), capacityDto.getPp(), capacityDto.getType(), capacityDto.getUseCount());
    }

    public CapacityDTO toDto() {
        return new CapacityDTO(name, category, power, accuracy, pp, type, useCount, null);
    }
}
