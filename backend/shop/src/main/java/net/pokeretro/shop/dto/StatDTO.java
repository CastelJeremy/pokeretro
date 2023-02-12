package net.pokeretro.shop.dto;

public class StatDTO {
    private Integer hp;
    private Integer attack;
    private Integer defense;
    private Integer speed;
    private Integer special;

    public StatDTO() {
    }

    public StatDTO(Integer hp, Integer attack, Integer defense, Integer speed, Integer special) {
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.special = special;
    }

    public Integer getHp() {
        return hp;
    }

    public Integer getAttack() {
        return attack;
    }

    public Integer getDefense() {
        return defense;
    }

    public Integer getSpeed() {
        return speed;
    }

    public Integer getSpecial() {
        return special;
    }
}