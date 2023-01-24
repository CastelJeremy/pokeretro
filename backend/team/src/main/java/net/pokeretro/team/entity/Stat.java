package net.pokeretro.team.entity;

public class Stat {
    private Long hp;
    private Long attack;
    private Long defense;
    private Long specialAttack;
    private Long specialDefense;
    private Long speed;

    public Stat() {
    }

    public Stat(Long hp, Long attack, Long defense, Long specialAttack, Long specialDefense, Long speed) {
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
        this.speed = speed;
    }

    public Long getHp() {
        return this.hp;
    }

    public Long getAttack() {
        return this.attack;
    }

    public Long getDefense() {
        return this.defense;
    }

    public Long getSpecialAttack() {
        return this.specialAttack;
    }

    public Long getSpecialDefense() {
        return this.specialDefense;
    }

    public Long getSpeed() {
        return this.speed;
    }
}
