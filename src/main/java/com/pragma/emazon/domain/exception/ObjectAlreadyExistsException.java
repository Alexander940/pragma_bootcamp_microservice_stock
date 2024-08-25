package com.pragma.emazon.domain.exception;

public class ObjectAlreadyExistsException extends RuntimeException{
    private final transient Object object;
    private final transient String uniqueAttribute;

    public ObjectAlreadyExistsException(Object object, String uniqueAttribute) {
        this.object = object;
        this.uniqueAttribute = uniqueAttribute;
    }

    public Object getObject() {
        return object;
    }

    public String getUniqueAttribute() {
        return uniqueAttribute;
    }
}
