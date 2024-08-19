package com.pragma.emazon.application.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ExceptionResponse {
    STRING_TOO_LONG(1 ,"The String length is too long", HttpStatus.BAD_REQUEST, LogLevel.INFO),
    MANDATORY_PARAMETER(2, "Mandatory parameter is missing", HttpStatus.BAD_REQUEST, LogLevel.INFO),
    CATEGORY_ALREADY_EXISTS(3 ,"Category already exists", HttpStatus.CONFLICT, LogLevel.INFO),
    BRAND_ALREADY_EXISTS(4 ,"Brand already exists", HttpStatus.CONFLICT, LogLevel.INFO);

    private final int code;
    private final String message;
    private final HttpStatus responseStatus;
    private final LogLevel logLevel;
}
