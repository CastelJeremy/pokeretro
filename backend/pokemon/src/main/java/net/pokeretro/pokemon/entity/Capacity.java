package net.pokeretro.pokemon.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "capacities")
public class Capacity {
    @Id
    private Integer id;

    @Column(nullable = false, length = 32)
    private String name;

    @Column(nullable = false, length = 32)
    private String category;

    private Integer power;

    private Integer accuracy;

    private Integer pp;

    @OneToOne
    @JoinColumn(name = "id_type", referencedColumnName = "id")
    private Type type;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public String getCategory() {
        return this.category;
    }

    public Integer getPower() {
        return this.power;
    }

    public Integer getAccuracy() {
        return this.accuracy;
    }

    public Integer getPp() {
        return this.pp;
    }

    public Type getType() {
        return this.type;
    }
}
