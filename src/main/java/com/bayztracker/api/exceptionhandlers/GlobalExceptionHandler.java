package com.bayztracker.api.exceptionhandlers;

import com.bayztracker.api.exceptions.NotFoundException;
import com.bayztracker.api.exceptions.UnsupportedCurrencyCreationException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.badRequest().body(new ErrorMessage(HttpStatus.BAD_REQUEST));
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.badRequest().body(new ErrorMessage(HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> handleNotFound(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(HttpStatus.NOT_FOUND, ex.getMessage())); // TODO: 10/15/2022 instantiate errorMessageProperly
    }

    @ExceptionHandler(UnsupportedCurrencyCreationException.class)
    public ResponseEntity<ErrorMessage> handleUnsupportedCurrencyCreation(UnsupportedCurrencyCreationException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorMessage(HttpStatus.CONFLICT, ex.getMessage())); // TODO: 10/15/2022 instantiate errorMessageProperly
    }
}
