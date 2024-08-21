package com.pragma.emazon.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ObjectAlreadyExistsException extends RuntimeException{
    private final transient Object object;
    private final transient String uniqueAttribute;
}
