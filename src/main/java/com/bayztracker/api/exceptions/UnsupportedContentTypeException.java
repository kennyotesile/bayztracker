package com.bayztracker.api.exceptions;

public class UnsupportedContentTypeException extends RuntimeException {
    public UnsupportedContentTypeException(String message) {
        super(message);
    }
}
