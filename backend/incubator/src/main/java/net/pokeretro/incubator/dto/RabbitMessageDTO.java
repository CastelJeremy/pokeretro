package net.pokeretro.incubator.dto;

import java.io.Serializable;
import java.util.UUID;

public class RabbitMessageDTO implements Serializable {
    private UUID trainerId;
    private PokemonDTO pokemon;

    public RabbitMessageDTO() {}

    public RabbitMessageDTO(UUID trainerId, PokemonDTO pokemon) {
        this.trainerId = trainerId;
        this.pokemon = pokemon;
    }

    public UUID getTrainerId() {
        return trainerId;
    }

    public PokemonDTO getPokemon() {
        return pokemon;
    }
}
