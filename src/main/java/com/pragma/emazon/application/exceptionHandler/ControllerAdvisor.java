package com.pragma.emazon.application.exceptionHandler;

import com.pragma.emazon.application.exception.CategoryAlreadyExistsException;
import com.pragma.emazon.application.exception.MandatoryParameterException;
import com.pragma.emazon.application.exception.StringTooLongException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

public class ControllerAdvisor {

    private static final String MESSAGE = "message";

    @ExceptionHandler(StringTooLongException.class)
    public ResponseEntity<Map<String, String>> handleStringTooLongException(
            StringTooLongException stringTooLongException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.STRING_TOO_LONG.getMessage()));
    }

    @ExceptionHandler(MandatoryParameterException.class)
    public ResponseEntity<Map<String, String>> handleMandatoryParameterException(
            MandatoryParameterException mandatoryParameterException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.MANDATORY_PARAMETER.getMessage()));
    }

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleCategoryAlreadyExistsException(
            CategoryAlreadyExistsException categoryAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, com.pragma.emazon.infrastructure.exceptionhandler.ExceptionResponse.CATEGORY_ALREADY_EXISTS.getMessage()));
    }
}
