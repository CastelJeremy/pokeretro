package net.pokeretro.trainer.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "trainers")
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false, length = 16)
    private String name;

    @Column(nullable = false, length = 8)
    private String gender;

    @Column(nullable = false)
    private Integer starter;

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public Integer getStarter() {
        return starter;
    }
}