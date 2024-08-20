package com.pragma.emazon.application.exceptionhandler;

import com.pragma.emazon.application.exception.ObjectAlreadyExistsException;
import com.pragma.emazon.application.exception.MandatoryParameterException;
import com.pragma.emazon.application.exception.StringTooLongException;
import com.pragma.emazon.application.util.ObjectUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

public class ControllerAdvisor {

    @ExceptionHandler(StringTooLongException.class)
    public ResponseEntity<Map<String, String>> handleStringTooLongException(
            StringTooLongException stringTooLongException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(ExceptionResponse.STRING_TOO_LONG.getMessage(),
                        "'" + stringTooLongException.getAttributeName() + "' " +
                        "length should be less than " + stringTooLongException.getMaxLength()));
    }

    @ExceptionHandler(MandatoryParameterException.class)
    public ResponseEntity<Map<String, String>> handleMandatoryParameterException(
            MandatoryParameterException mandatoryParameterException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(ExceptionResponse.MANDATORY_PARAMETER.getMessage(),
                        "'" + mandatoryParameterException.getMandatoryParameter() + "' " +
                        "can't be null or empty"));
    }

    @ExceptionHandler(ObjectAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleCategoryAlreadyExistsException(
            ObjectAlreadyExistsException objectAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(ExceptionResponse.OBJECT_ALREADY_EXISTS.getMessage(),
                        ObjectUtil.getClassName(objectAlreadyExistsException.getObject()) + " with this " +
                        objectAlreadyExistsException.getUniqueAttribute() + " already exists"));
    }
}
