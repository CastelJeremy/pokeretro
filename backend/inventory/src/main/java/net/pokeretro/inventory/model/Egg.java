package net.pokeretro.inventory.model;

import jakarta.persistence.*;

@Entity
@Table(name = "eggs")
public class Egg {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;



    public Long getId() {
        return id;
    }
}
