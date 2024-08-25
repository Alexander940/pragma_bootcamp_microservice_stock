package com.pragma.emazon.domain.exception;

public class MandatoryParameterException extends RuntimeException{
    private final transient String mandatoryParameter;

    public MandatoryParameterException(String mandatoryParameter) {
        this.mandatoryParameter = mandatoryParameter;
    }

    public String getMandatoryParameter() {
        return mandatoryParameter;
    }
}
