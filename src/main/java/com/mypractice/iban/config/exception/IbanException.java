package com.mypractice.iban.config.exception;

public class IbanException extends RuntimeException{
    public IbanException(String message){
        super(message);
    }
}
