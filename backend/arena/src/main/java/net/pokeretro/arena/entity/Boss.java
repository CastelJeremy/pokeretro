package net.pokeretro.arena.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import net.pokeretro.arena.dto.BossDTO;

@Entity(name = "bosses")
public class Boss {
    @Id
    private UUID id;

    @Column(nullable = false, length = 16)
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_next_boss", nullable = true)
    private Boss nextBoss;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boss getNextBoss() {
        return nextBoss;
    }

    public BossDTO toDto() {
        UUID nextBossId = null;

        if (nextBoss != null) {
            nextBossId = nextBoss.getId();
        }

        return new BossDTO(id, name, nextBossId);
    }
}
