package io.pp.arcade.domain.noti.dto;

import io.pp.arcade.global.type.NotiType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class NotiMatchedDto {
    private Integer id;
    private NotiType type;
    private LocalDateTime time;
    private Boolean isChecked;
    private LocalDateTime createdAt;
}
