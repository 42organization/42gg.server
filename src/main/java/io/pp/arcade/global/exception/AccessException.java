package io.pp.arcade.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AccessException extends RuntimeException{
    private String redirectUrl;
}
