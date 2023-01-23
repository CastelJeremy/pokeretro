package net.pokeretro.pokemon.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "capacity")
public class Capacity {
    @Id
    private Long id;

    @Column(nullable = false, length = 32)
    private String name;

    @Column(nullable = false, length = 32)
    private String category;

    @Column(nullable = false)
    private Long power;

    @Column(nullable = false)
    private Long accuracy;

    @Column(nullable = false)
    private Long pp;

    @OneToOne(targetEntity = Type.class)
    @JoinColumn(name = "id_type", referencedColumnName = "id")
    private Type type;

    public String getName() {
        return this.name;
    }

    public String getCategory() {
        return this.category;
    }

    public Long getPower() {
        return this.power;
    }

    public Long getAccuracy() {
        return this.accuracy;
    }

    public Long getPp() {
        return this.pp;
    }

    public Type getType() {
        return this.type;
    }
}
