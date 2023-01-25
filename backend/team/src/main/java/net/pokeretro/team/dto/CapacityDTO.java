package net.pokeretro.team.dto;

public class CapacityDTO {
    private String name;
    private String category;
    private Integer power;
    private Integer accuracy;
    private Integer pp;
    private String type;

    public CapacityDTO(String name, String category, Integer power, Integer accuracy, Integer pp, String type) {
        this.name = name;
        this.category = category;
        this.power = power;
        this.accuracy = accuracy;
        this.pp = pp;
        this.type = type;
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
}