package com.pragma.emazon.domain.exception;

public class StringTooLongException extends RuntimeException {
    private final transient String attributeName;
    private final transient Integer maxLength;

    public StringTooLongException(String attributeName, Integer maxLength) {
        this.attributeName = attributeName;
        this.maxLength = maxLength;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public Integer getMaxLength() {
        return maxLength;
    }
}
