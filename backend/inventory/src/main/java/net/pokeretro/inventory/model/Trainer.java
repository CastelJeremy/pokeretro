package net.pokeretro.inventory.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "trainers")
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "gender", nullable = false)
    private String gender;
}
