package com.pragma.emazon.infrastructure.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ExceptionResponse {
    STRING_TOO_LONG(1 ,"The String length is too long", HttpStatus.BAD_REQUEST, LogLevel.INFO),
    MANDATORY_PARAMETER(2, "Mandatory parameter is missing", HttpStatus.BAD_REQUEST, LogLevel.INFO),
    OBJECT_ALREADY_EXISTS(3 ,"Category already exists", HttpStatus.CONFLICT, LogLevel.INFO),
    ASSOCIATED_ATTRIBUTE_NUMBER(4, "Associated attribute number is not valid", HttpStatus.BAD_REQUEST, LogLevel.INFO),
    REPEATED_ATTRIBUTE(5, "Repeated attribute", HttpStatus.BAD_REQUEST, LogLevel.INFO);

    private final int code;
    private final String message;
    private final HttpStatus responseStatus;
    private final LogLevel logLevel;
}
