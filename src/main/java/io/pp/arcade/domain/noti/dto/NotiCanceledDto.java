package io.pp.arcade.domain.noti.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class NotiCanceledDto {
    private Integer id;
    private String type;
    private LocalDateTime time;
    private Boolean isChecked;
    private LocalDateTime createdAt;
}