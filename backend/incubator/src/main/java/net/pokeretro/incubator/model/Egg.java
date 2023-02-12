package net.pokeretro.incubator.model;

import jakarta.persistence.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "eggs")
public class Egg {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID trainerId;
    private Integer time;
    private Date incubationStartDate;
    private Integer weight;
    private Integer pokemonId;

    public UUID getId() {
        return id;
    }

    public UUID getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(UUID trainerId) {
        this.trainerId = trainerId;
    }

    public Integer getTime() {
        return time;
    }

    public long getIncubationStartTime() {
        return incubationStartDate.getTime();
    }

    public void setIncubationStartDate(Date incubationStartDate) {
        this.incubationStartDate = incubationStartDate;
    }

    public boolean isIncubationFinished() {
        return incubationStartDate.toInstant().plus(time, ChronoUnit.SECONDS).isBefore(Instant.now());
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getPokemonId() {
        return pokemonId;
    }
}