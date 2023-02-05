package net.pokeretro.incubator.model;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "incubator")
public class Incubator {
    @Id
    @Column(name = "id_trainer", nullable = false)
    private UUID idTrainer;

    @OneToMany(mappedBy = "incubator")
    private final Collection<Egg> eggs = new java.util.ArrayList<>();

    public Incubator() {
    }

    public Incubator(UUID idTrainer) {
        this.idTrainer = idTrainer;
    }

    public Collection<Egg> getEggs() {
        return eggs;
    }

    public boolean addEgg(Egg egg) {
        if(getWeight() + egg.getWeight() <= 3000){ // impossible de mettre + de 3 kilo
            return eggs.add(egg);
        } else {
            return false;
        }
    }

    public UUID getIdTrainer() {
        return idTrainer;
    }

    /**
     *
     * @return la somme de tous les poids d'œufs, s'il n'y en a pas, on renvoie 0
     */
    public int getWeight(){
        return eggs.stream().map(Egg::getWeight).reduce(Integer::sum).orElse(0);
    }

    public IncubatorDTO toDTO() {
        return new IncubatorDTO(getIdTrainer(), getEggs().stream().map(Egg::toDto).collect(Collectors.toList()));
    }
}
