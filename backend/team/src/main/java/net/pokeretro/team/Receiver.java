package net.pokeretro.team;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.amqp.AmqpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.pokeretro.team.dto.CapacityDTO;
import net.pokeretro.team.dto.PokemonDTO;
import net.pokeretro.team.dto.StatDTO;
import net.pokeretro.team.exception.NotEnoughPlaceException;
import net.pokeretro.team.service.TeammateService;

@Component
public class Receiver {
    @Autowired
    TeammateService teammateService;

    public void receiveMessage(String message) {
        try {
            // Init json properties
            JSONObject json = new JSONObject(message);
            JSONObject pJson = json.getJSONObject("pokemon");
            JSONArray tArray = pJson.getJSONArray("types");
            JSONArray cArray = pJson.getJSONArray("capacities");
            JSONObject bJson = pJson.getJSONObject("baseStat");
            JSONObject iJson = pJson.getJSONObject("individualStat");

            // Convert types list
            List<String> types = new ArrayList<>();
            tArray.forEach(tJson -> types.add(tJson.toString()));

            // Convert capacity
            List<CapacityDTO> capacities = new ArrayList<>();

            for (int i = 0; i < cArray.length(); i++) {
                JSONObject cJson = cArray.getJSONObject(i);

                // Power and accuracy could be set to null and thus not be sent
                Integer power = null;
                Integer accuracy = null;

                try {
                    power = cJson.getInt("power");
                } catch (JSONException e) {
                }

                try {
                    power = cJson.getInt("accuracy");
                } catch (JSONException e) {
                }

                CapacityDTO capacity = new CapacityDTO(cJson.getString("name"), cJson.getString("category"),
                        power, accuracy, cJson.getInt("pp"), cJson.getString("type"), cJson.getInt("useCount"),
                        cJson.getInt("levelLearned"));
                capacities.add(capacity);
            }

            // Convert Stat
            StatDTO baseStat = new StatDTO(bJson.getInt("hp"), bJson.getInt("attack"), bJson.getInt("defense"),
                    bJson.getInt("speed"), bJson.getInt("special"));
            StatDTO individualStat = new StatDTO(iJson.getInt("hp"), iJson.getInt("attack"), iJson.getInt("defense"),
                    iJson.getInt("speed"), iJson.getInt("special"));

            // No evolution so evolutionId and Level could be null
            Integer evolutionId = null;
            Integer evolutionLevel = null;

            try {
                evolutionId = pJson.getInt("evolutionId");
            } catch (JSONException e) {
            }

            try {
                evolutionLevel = pJson.getInt("evolutionLevel");
            } catch (JSONException e) {
            }

            // Convert Pokemon and link previous conversion
            PokemonDTO pokemon = new PokemonDTO(pJson.getInt("id"), pJson.getString("name"), types, capacities,
                    baseStat, individualStat, evolutionLevel, evolutionId, pJson.getInt("rarity"));

            // Class service
            teammateService.addPokemon(UUID.fromString(json.getString("trainerId")), pokemon);
        } catch (NotEnoughPlaceException e) {
            throw new AmqpException("Not Enough Place");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
