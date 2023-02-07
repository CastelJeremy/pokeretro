package net.pokeretro.arena.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class TrainerBoss {
    @Id
    @Column(name = "id_trainer")
    private UUID trainerId;

    @ManyToOne
    @JoinColumn(name = "id_boss", nullable = true)
    private Boss boss;

    public TrainerBoss() {
    }

    public TrainerBoss(UUID trainerId, Boss boss) {
        this.trainerId = trainerId;
        this.boss = boss;
    }

    public UUID getTrainerId() {
        return trainerId;
    }

    public Boss getBoss() {
        return boss;
    }

    public void setBoss(Boss boss) {
        this.boss = boss;
    }
}
