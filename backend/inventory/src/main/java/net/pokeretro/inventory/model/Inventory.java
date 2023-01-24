package net.pokeretro.inventory.model;

import jakarta.persistence.*;
import net.pokeretro.inventory.exception.NotEnoughMoneyException;

import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "inventories")
public class Inventory {

    @Id
    @Column(name = "id_trainer", nullable = false)
    private UUID idTrainer;

    @Column(name = "money", nullable = false)
    private int money;

    @OneToMany
    private Collection<Egg> eggs;

    public UUID getIdTrainer() {
        return idTrainer;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) throws NotEnoughMoneyException {
        if(money < 0) throw new NotEnoughMoneyException();
        this.money = money;
    }

    public void addMoney(int amount) throws NotEnoughMoneyException {
        if(amount < 0 && this.money-amount < 0) throw new NotEnoughMoneyException();
        this.money += amount;
    }

    public Collection<Egg> getEggs() {
        return eggs;
    }

    public void setEggs(Collection<Egg> eggs) {
        this.eggs = eggs;
    }
}
