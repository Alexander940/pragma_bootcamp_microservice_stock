package com.pragma.emazon.application.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MandatoryParameterException extends RuntimeException{
    private final transient String mandatoryParameter;
}
