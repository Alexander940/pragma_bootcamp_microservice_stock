package com.pragma.emazon.domain.exception;

public class AssociatedAttributesNumberException extends RuntimeException{
    private final transient String attributeName;
    private final transient String associatedAttributesNumber;

    public AssociatedAttributesNumberException(String attributeName, String associatedAttributesNumber) {
        this.attributeName = attributeName;
        this.associatedAttributesNumber = associatedAttributesNumber;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public String getAssociatedAttributesNumber() {
        return associatedAttributesNumber;
    }
}
