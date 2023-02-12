package net.pokeretro.team.entity;

import net.pokeretro.team.dto.StatDTO;

public class Stat {
    private Integer hp;
    private Integer attack;
    private Integer defense;
    private Integer speed;
    private Integer special;

    public Stat() {
    }

    public Stat(Integer hp, Integer attack, Integer defense, Integer speed, Integer special) {
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

    public static Stat fromDto(StatDTO statDTO) {
        return new Stat(
                statDTO.getHp(),
                statDTO.getAttack(),
                statDTO.getDefense(),
                statDTO.getSpeed(),
                statDTO.getSpecial());
    }

    public StatDTO toDto() {
        return new StatDTO(hp, attack, defense, speed, special);
    }
}
