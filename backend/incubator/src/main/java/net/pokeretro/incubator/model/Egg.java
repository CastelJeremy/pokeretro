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
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "time")
    private Integer time;

    @Column(name = "incubation_start_date")
    private Date incubationStartDate;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "id_pokemon")
    private Integer idPokemon;

    @ManyToOne
    @JoinColumn(name = "incubator_id_trainer")
    private Incubator incubator;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Egg() {
    }

    public Egg(Integer time, Date incubationStartDate, Integer weight, Integer idPokemon, Incubator incubator) {
        this.time = time;
        this.incubationStartDate = incubationStartDate;
        this.weight = weight;
        this.idPokemon = idPokemon;
        this.incubator = incubator;
    }

    public Egg(EggDTO dto, Incubator incubator) {
        this.time = dto.getTime();
        this.incubationStartDate = dto.getIncubationStartDate();
        this.weight = dto.getWeight();
        this.idPokemon = dto.getIdPokemon();
        this.incubator = incubator;
    }

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

    public void setIncubationStartDate(Date incubationStartDate) {
        this.incubationStartDate = incubationStartDate;
    }

    public Long getTimeLeft() {
        long start = incubationStartDate.toInstant().getEpochSecond();
        long end = incubationStartDate.toInstant().plus(time, ChronoUnit.SECONDS).getEpochSecond();
        return end - start;
    }

    public boolean isIncubationFinished() {
        return incubationStartDate != null &&
                incubationStartDate.toInstant().plus(time, ChronoUnit.SECONDS).isBefore(Instant.now());
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getIdPokemon() {
        return idPokemon;
    }

    @Override
    public String toString() {
        return "Egg{" +
                "id=" + id +
                ", time=" + time +
                ", incubationStartDate=" + incubationStartDate +
                ", weight=" + weight +
                ", idPokemon=" + idPokemon +
                ", incubator=" + incubator +
                '}';
    }

    public EggDTO toDto(){
        return new EggDTO(getId(), getTime(), getIncubationStartDate(), getWeight(), getIdPokemon());
    }
}