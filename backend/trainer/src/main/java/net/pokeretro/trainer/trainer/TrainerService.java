package net.pokeretro.trainer.trainer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public class TrainerService {
    @Autowired
    private TrainerRepository trainerRepository;

    public Optional<Trainer> getTrainer(final UUID id) {
        return trainerRepository.findById(id);
    }

    public Collection<Trainer> getTrainers() {
        return trainerRepository.findAll();
    }

    public Optional<Trainer> findIdByUsername(final String username) {
        return trainerRepository.findIdByUsername(username);
    }

    public void deleteTrainer(final UUID id) {
        trainerRepository.deleteById(id);
    }

    public Trainer saveTrainer(Trainer trainer) {
        return trainerRepository.save(trainer);
    }
}
