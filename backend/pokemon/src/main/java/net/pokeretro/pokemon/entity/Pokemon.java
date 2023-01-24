package net.pokeretro.pokemon.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pokemons")
public class Pokemon {
    @Id
    private Long id;

    @Column(nullable = false, length = 64)
    private String name;

    @Column(nullable = false)
    private Integer hp;

    @Column(nullable = false)
    private Integer attack;

    @Column(nullable = false)
    private Integer defense;

    @Column(nullable = false)
    private Integer specialAttack;

    @Column(nullable = false)
    private Integer specialDefense;

    @Column(nullable = false)
    private Integer speed;

    @ManyToMany
    @JoinTable(name = "pokemon_type", joinColumns = @JoinColumn(name = "id_pokemon"), inverseJoinColumns = @JoinColumn(name = "id_type"))
    private List<Type> types;

    @OneToMany(mappedBy = "pokemon")
    private List<PokemonCapacity> capacities;

    private Integer evolutionLevel;

    @ManyToOne
    @JoinColumn(name = "id_evolution")
    private Pokemon evolution;

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public List<Type> getTypes() {
        return this.types;
    }

    public List<PokemonCapacity> getCapacities() {
        return this.capacities;
    }

    public Integer getHp() {
        return this.hp;
    }

    public Integer getAttack() {
        return this.attack;
    }

    public Integer getDefense() {
        return this.defense;
    }

    public Integer getSpecialAttack() {
        return this.specialAttack;
    }

    public Integer getSpecialDefense() {
        return this.specialDefense;
    }

    public Integer getSpeed() {
        return this.speed;
    }

    public Integer getEvolutionLevel() {
        return this.evolutionLevel;
    }

    public Pokemon getEvolution() {
        return this.evolution;
    }
}
