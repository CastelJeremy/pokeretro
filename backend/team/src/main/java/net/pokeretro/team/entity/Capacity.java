package net.pokeretro.team.entity;

import net.pokeretro.team.dto.CapacityDTO;

public class Capacity {
    private String name;
    private String category;
    private Integer power;
    private Integer accuracy;
    private Integer pp;
    private String type;

    public Capacity() {
    }

    public Capacity(String name, String category, Integer power, Integer accuracy, Integer pp, String type) {
        this.name = name;
        this.category = category;
        this.power = power;
        this.accuracy = accuracy;
        this.pp = pp;
        this.type = type;
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

    public static Capacity fromDto(CapacityDTO capacityDTO) {
        return new Capacity(
                capacityDTO.getName(),
                capacityDTO.getCategory(),
                capacityDTO.getPower(),
                capacityDTO.getAccuracy(),
                capacityDTO.getPp(),
                capacityDTO.getType());
    }
}
