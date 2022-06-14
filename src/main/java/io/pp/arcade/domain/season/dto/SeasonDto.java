package io.pp.arcade.domain.season.dto;

import io.pp.arcade.domain.season.Season;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class SeasonDto {
    private Integer id;
    private String seasonName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer startPpp;

    public static SeasonDto from(Season season) {
        return SeasonDto.builder()
                .id(season.getId())
                .seasonName(season.getSeasonName())
                .startTime(season.getStartTime())
                .endTime(season.getEndTime())
                .startPpp(season.getStartPpp())
                .build();
    }
}