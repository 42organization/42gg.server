package io.pp.arcade.global.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Locale;

@Getter
@RequiredArgsConstructor
public enum StatusType implements Constant{
    LIVE("live"),
    WAIT("wait"),
    END("end");

    private final String code;

    @JsonCreator
    public static StatusType getEnumFromValue(String value) {
        for(StatusType e : values()) {
            if(e.name().equals(value)) {
                return e;
            }
            else if (e.code.toUpperCase(Locale.ROOT).equals(value.toUpperCase(Locale.ROOT))) {
                return e;
            }
        }
        return null;
    }
}
