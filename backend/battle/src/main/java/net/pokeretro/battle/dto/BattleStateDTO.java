package net.pokeretro.battle.dto;

import java.util.List;
import java.util.UUID;

public class BattleStateDTO {
    private UUID id;
    private Integer turn;
    private String status;
    private Integer currentFighter;
    private Integer currentOpponent;
    private List<TeammateDTO> fighterTeam;
    private List<TeammateDTO> opponentTeam;

    public BattleStateDTO(UUID id, Integer turn, String status, Integer currentFighter, Integer currentOpponent,
            List<TeammateDTO> fighterTeam,
            List<TeammateDTO> opponentTeam) {
        this.id = id;
        this.turn = turn;
        this.status = status;
        this.currentFighter = currentFighter;
        this.currentOpponent = currentOpponent;
        this.fighterTeam = fighterTeam;
        this.opponentTeam = opponentTeam;
    }

    public UUID getId() {
        return id;
    }

    public Integer getTurn() {
        return turn;
    }

    public String getStatus() {
        return status;
    }

    public Integer getCurrentFighter() {
        return currentFighter;
    }

    public Integer getCurrentOpponent() {
        return currentOpponent;
    }

    public List<TeammateDTO> getFighterTeam() {
        return fighterTeam;
    }

    public List<TeammateDTO> getOpponentTeam() {
        return opponentTeam;
    }
}
