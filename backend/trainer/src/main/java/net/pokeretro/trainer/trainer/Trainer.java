package net.pokeretro.trainer.trainer;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "trainers")
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(nullable = false, length = 64, unique = true)
    private String username;

    @Column(nullable = false, length = 64)
    private String gender;
    @Column(nullable = false, length = 64)
    private int starter;

    public Trainer() {
    }
    public Trainer(String username, String gender, int starter) {
        this.username = username;
        this.gender = gender;
        this.starter = starter;
    }

    public String getUsername() {
        return username;
    }

    public String getGender() {
        return gender;
    }

    public int getStarter() {
        return starter;
    }

    public UUID getId() {
        return id;
    }
}