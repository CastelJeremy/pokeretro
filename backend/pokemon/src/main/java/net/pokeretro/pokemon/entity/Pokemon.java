package net.pokeretro.pokemon.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pokemon")
public class Pokemon {
    @Id
    private Long id;

    @Column(nullable = false, length = 64)
    private String name;

    @Column(nullable = false)
    private Long hp;

    @Column(nullable = false)
    private Long attack;

    @Column(nullable = false)
    private Long defense;

    @Column(nullable = false)
    private Long specialAttack;

    @Column(nullable = false)
    private Long specialDefense;

    @Column(nullable = false)
    private Long speed;

    @ManyToMany(targetEntity = Type.class)
    @JoinTable(name = "pokemon_type", joinColumns = @JoinColumn(name = "id_pokemon"), inverseJoinColumns = @JoinColumn(name = "id_type"))
    private List<Type> types;

    @ManyToMany(targetEntity = Capacity.class)
    @JoinTable(name = "pokemon_capacity", joinColumns = @JoinColumn(name = "id_pokemon"), inverseJoinColumns = @JoinColumn(name = "id_capacity"))
    private List<Capacity> capacities;

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public List<Type> getTypes() {
        return this.types;
    }

    public List<Capacity> getCapacities() {
        return this.capacities;
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
