package com.bayztracker.api.exceptions;

public class UnsupportedCurrencyCreationException extends RuntimeException {
    public UnsupportedCurrencyCreationException(String message) {
        super(message);
    }
}
