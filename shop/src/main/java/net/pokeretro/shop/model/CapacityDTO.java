package net.pokeretro.shop.model;

public class CapacityDTO {
    private String name;
    private String category;
    private Integer power;
    private Integer accuracy;
    private Integer pp;
    private String type;
    private Integer levelLearned;

    public CapacityDTO(String name, String category, Integer power, Integer accuracy, Integer pp, String type, Integer levelLearned) {
        this.name = name;
        this.category = category;
        this.power = power;
        this.accuracy = accuracy;
        this.pp = pp;
        this.type = type;
        this.levelLearned = levelLearned;
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

    public Integer getLevelLearned() {
        return levelLearned;
    }
}