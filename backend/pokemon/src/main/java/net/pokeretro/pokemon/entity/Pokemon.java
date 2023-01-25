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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import net.pokeretro.pokemon.dto.CapacityDTO;
import net.pokeretro.pokemon.dto.PokemonDTO;
import net.pokeretro.pokemon.dto.StatDTO;

@Entity
@Table(name = "pokemons")
public class Pokemon {
    @Id
    private Integer id;

    @Column(nullable = false, length = 64)
    private String name;

    @ManyToMany
    @JoinTable(name = "pokemon_type", joinColumns = @JoinColumn(name = "id_pokemon"), inverseJoinColumns = @JoinColumn(name = "id_type"))
    private List<Type> types;

    @OneToMany(mappedBy = "pokemon")
    private List<PokemonCapacity> capacities;

    @OneToOne
    @JoinColumn(name = "id_stat")
    private Stat baseStat;

    @Transient
    private Stat individualStat;

    private Integer evolutionLevel;

    @ManyToOne
    @JoinColumn(name = "id_evolution")
    private Pokemon evolution;

    public Integer getId() {
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

    public Stat getBaseStat() {
        return baseStat;
    }

    public Stat getIndividualStat() {
        return individualStat;
    }

    public void setIndividualStat(Stat individualStat) {
        this.individualStat = individualStat;
    }

    public Integer getEvolutionLevel() {
        return this.evolutionLevel;
    }

    public Pokemon getEvolution() {
        return this.evolution;
    }

    public PokemonDTO toDto() {
        List<CapacityDTO> capacities = this.capacities.stream().map(capacity -> capacity.toDto()).toList();
        List<String> types = this.types.stream().map(type -> type.getName()).toList();
        Integer evolutionId = this.evolution != null ? this.evolution.getId() : null;
        StatDTO individualStat = this.individualStat != null ? this.individualStat.toDto() : null;

        return new PokemonDTO(this.id, this.name, types, capacities, this.baseStat.toDto(), individualStat, this.evolutionLevel, evolutionId);
    }
}
