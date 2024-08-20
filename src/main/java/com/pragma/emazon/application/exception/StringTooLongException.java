package com.pragma.emazon.application.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class StringTooLongException extends RuntimeException {
    private final transient String attributeName;
    private final transient Integer maxLength;
}
