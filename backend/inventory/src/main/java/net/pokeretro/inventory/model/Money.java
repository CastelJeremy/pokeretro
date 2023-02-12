package net.pokeretro.inventory.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "moneys")
public class Money {
    @Id
    @Column(name = "id_trainer")
    private UUID trainerId;

    @Column(nullable = false)
    private Integer amount;

    public void setTrainerId(UUID trainerId) {
        this.trainerId = trainerId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
