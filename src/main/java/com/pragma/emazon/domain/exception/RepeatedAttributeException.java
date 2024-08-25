package com.pragma.emazon.domain.exception;

public class RepeatedAttributeException extends RuntimeException{
    private final transient String attributeName;

    public RepeatedAttributeException(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeName() {
        return attributeName;
    }
}
