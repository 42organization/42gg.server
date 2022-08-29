package io.pp.arcade.domain.game.dto;

import io.pp.arcade.domain.game.Manager.data.GameTeam;
import io.pp.arcade.global.type.GameType;
import io.pp.arcade.global.type.Mode;
import io.pp.arcade.global.type.StatusType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GameResultDto {
    private Integer gameId;
    private String type;
    private String status;
    private String mode;
    private LocalDateTime time;
    private GameTeam team1;
    private GameTeam team2;

    @Builder
    public GameResultDto(Integer gameId, GameType type, StatusType status, Mode mode, LocalDateTime time, GameTeam team1, GameTeam team2) {
        this.gameId = gameId;
        this.type = type.getCode();
        this.status = status.getCode();
        this.mode = mode.getCode();
        this.time = time;
        this.team1 = team1;
        this.team2 = team2;
    }

    @Override
    public String toString() {
        return "GameResultDto{" +
                "gameId=" + gameId +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", time=" + time +
                ", team1=" + team1 +
                ", team2=" + team2 +
                '}';
    }
}
