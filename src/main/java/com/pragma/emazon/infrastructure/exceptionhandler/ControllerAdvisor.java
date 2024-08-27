package com.pragma.emazon.infrastructure.exceptionhandler;

import com.pragma.emazon.domain.exception.*;
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

    @ExceptionHandler(AssociatedAttributesNumberException.class)
    public ResponseEntity<Map<String, String>> handleAssociatedAttributeNumberException(
            AssociatedAttributesNumberException associatedAttributeNumberException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(ExceptionResponse.ASSOCIATED_ATTRIBUTE_NUMBER.getMessage(),
                       associatedAttributeNumberException.getAttributeName() + " number should be between " +
                                associatedAttributeNumberException.getAssociatedAttributesNumber()));
    }

    @ExceptionHandler(RepeatedAttributeException.class)
    public ResponseEntity<Map<String, String>> handleRepeatedAttributeException(
            RepeatedAttributeException repeatedAttributeException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(ExceptionResponse.REPEATED_ATTRIBUTE.getMessage(),
                        repeatedAttributeException.getAttributeName() + " should not be repeated"));
    }
}
