package net.pokeretro.pokemon.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import net.pokeretro.pokemon.dto.StatDTO;

@Entity
@Table(name = "stats")
public class Stat {
    @Id
    private Integer id;

    @Column(nullable = false)
    private Integer hp;

    @Column(nullable = false)
    private Integer attack;

    @Column(nullable = false)
    private Integer defense;

    @Column(nullable = false)
    private Integer speed;

    @Column(nullable = false)
    private Integer special;

    public Stat() {}

    public Stat(Integer id, Integer hp, Integer attack, Integer defense, Integer speed, Integer special) {
        this.id = id;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.special = special;
    }

    public Integer getId() {
        return id;
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

    public StatDTO toDto() {
        return new StatDTO(this.hp, this.attack, this.defense, this.speed, this.special);
    }
}
