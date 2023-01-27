package net.pokeretro.shop.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "trainers")
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "username",nullable = false, length = 64, unique = true)
    private String username;

    @Column(name = "gender" , nullable = false, length = 64)
    private String gender;

    public Trainer() {
    }
    public Trainer(String username, String gender) {
        this.username = username;
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public String getGender() {
        return gender;
    }

    public UUID getId() {
        return id;
    }
}