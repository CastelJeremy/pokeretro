package net.pokeretro.team.entity;

import java.util.List;

public class PokemonDTO {
    private Long id;
    private String name;
    private Long hp;
    private Long attack;
    private Long defense;
    private Long specialAttack;
    private Long specialDefense;
    private Long speed;
    private List<Type> types;
    // private List<Capacity> capacities;

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    // public List<Capacity> getCapacities() {
    //     return this.capacities;
    // }

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

    public List<Type> getTypes() {
        return this.types;
    }
}