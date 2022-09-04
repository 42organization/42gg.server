package io.pp.arcade.v1.domain.rank.dto;


import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class RankListResponseDto {
    private Integer myRank;
    private Integer currentPage;
    private Integer totalPage;
    private List<RankUserDto> rankList;

    @Override
    public String toString() {
        return "RankListResponseDto{" +
                ", myRank=" + myRank +
                ", currentPage=" + currentPage +
                ", totalPage=" + totalPage +
                ", rankList=" + rankList +
                '}';
    }
}