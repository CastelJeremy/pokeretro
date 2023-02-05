package net.pokeretro.incubator.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class IncubatorDTO {
    private UUID idTrainer;
    private Collection<EggDTO> eggs;

    public IncubatorDTO(UUID idTrainer) {
        this.idTrainer = idTrainer;
        eggs = new ArrayList<>();
    }

    public IncubatorDTO(UUID idTrainer, Collection<EggDTO> eggs) {
        this.idTrainer = idTrainer;
        this.eggs = eggs;
    }

    public UUID getIdTrainer() {
        return idTrainer;
    }

    public Collection<EggDTO> getEggs() {
        return eggs;
    }
}
