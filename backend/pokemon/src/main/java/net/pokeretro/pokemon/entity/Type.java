package net.pokeretro.pokemon.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Type {
    @Id
    private Long id;

    @Column(nullable = false, length = 32)
    private String name;

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}
