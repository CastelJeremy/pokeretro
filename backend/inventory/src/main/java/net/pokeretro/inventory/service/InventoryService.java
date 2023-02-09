package net.pokeretro.inventory.service;

import net.pokeretro.inventory.exception.NotEnoughMoneyException;
import net.pokeretro.inventory.model.Egg;
import net.pokeretro.inventory.model.Money;
import net.pokeretro.inventory.repository.EggRepository;
import net.pokeretro.inventory.repository.MoneyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class InventoryService {
    @Autowired
    private EggRepository eggRepository;

    @Autowired
    private MoneyRepository moneyRepository;

    public Money getMoney(UUID trainerId) {
        Optional<Money> optMoney = moneyRepository.findById(trainerId);

        Money money;
        if (optMoney.isEmpty()) {
            money = new Money();
            money.setTrainerId(trainerId);
            money.setAmount(20000);

            moneyRepository.save(money);
        } else {
            money = optMoney.get();
        }

        return money;
    }

    public Money addMoney(UUID trainerId, Money money) {
        Money currentMoney = this.getMoney(trainerId);

        currentMoney.setAmount(currentMoney.getAmount() + money.getAmount());
        moneyRepository.save(currentMoney);

        return currentMoney;
    }

    public Money removeMoney(UUID trainerId, Money money) throws NotEnoughMoneyException {
        Money currentMoney = this.getMoney(trainerId);

        if (currentMoney.getAmount() >= money.getAmount()) {
            currentMoney.setAmount(currentMoney.getAmount() - money.getAmount());
            moneyRepository.save(currentMoney);

            return currentMoney;
        }

        throw new NotEnoughMoneyException();
    }

    public Egg addEgg(UUID trainerId, Egg egg) {
        egg.setTrainerId(trainerId);
        eggRepository.save(egg);

        return egg;
    }

    public Egg removeEgg(Egg egg) {
        eggRepository.delete(egg);

        return egg;
    }
}
