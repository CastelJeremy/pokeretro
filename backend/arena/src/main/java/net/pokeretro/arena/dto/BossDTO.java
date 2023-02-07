package net.pokeretro.arena.dto;

import java.util.UUID;

public class BossDTO {
    private UUID id;

    private String name;

    private UUID nextBossId;

    public BossDTO() {
    }

    public BossDTO(UUID id, String name, UUID nextBossId) {
        this.id = id;
        this.name = name;
        this.nextBossId = nextBossId;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public UUID getNextBossId() {
        return nextBossId;
    }
}
