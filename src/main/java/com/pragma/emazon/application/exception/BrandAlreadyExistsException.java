package com.pragma.emazon.application.exception;

public class BrandAlreadyExistsException extends RuntimeException{
    public BrandAlreadyExistsException(){
        super("Brand already exists");
    }
}
