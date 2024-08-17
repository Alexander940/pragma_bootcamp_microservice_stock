package com.pragma.emazon.infrastructure.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ExceptionResponse {
    CATEGORY_ALREADY_EXISTS(1 ,"Category already exists", HttpStatus.CONFLICT, LogLevel.INFO);

    private final int code;
    private final String message;
    private final HttpStatus responseStatus;
    private final LogLevel logLevel;
}
