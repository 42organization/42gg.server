package io.pp.arcade.domain.slot.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class SlotFindStatusRequestDto {
    private LocalDateTime currentTime; //앞 시간들은 닫혀있어야 하니까
    private Integer userId; //user정보 조회(userPpp, mytable에 user가 있는지 등)
    private String type;
}