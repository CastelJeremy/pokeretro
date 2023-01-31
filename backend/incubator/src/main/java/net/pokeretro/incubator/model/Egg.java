package net.pokeretro.incubator.model;

import jakarta.persistence.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Entity
@Table(name = "eggs")
public class Egg {

    @Column(name = "time")
    private Integer time;

    @Column(name = "incubation_start_date")
    private Date incubationStartDate;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "id_pokemon")
    private Integer idPokemon;

    @Id
    @ManyToOne
    @JoinColumn(name = "incubator_id_trainer")
    private Incubator incubator;

    public Incubator getIncubator() {
        return incubator;
    }

    public void setIncubator(Incubator incubator) {
        this.incubator = incubator;
    }

    public Integer getTime() {
        return time;
    }

    public Date getIncubationStartDate() {
        return incubationStartDate;
    }

    public Long getTimeLeft() {
        long start = incubationStartDate.toInstant().getEpochSecond();
        long end = incubationStartDate.toInstant().plus(time, ChronoUnit.SECONDS).getEpochSecond();
        return end - start;
    }

    public boolean isIncubationFinished() {
        if(incubationStartDate != null &&
                incubationStartDate.toInstant().plus(time, ChronoUnit.SECONDS).isAfter(Instant.now()) ) {
            return true;
        } else {
            return false;
        }
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getIdPokemon() {
        return idPokemon;
    }
}