package com.bayztracker.api.exceptions;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
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

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(new ErrorMessage(HttpStatus.METHOD_NOT_ALLOWED, ex.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> handleNotFound(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(UnsupportedCurrencyCreationException.class)
    public ResponseEntity<ErrorMessage> handleUnsupportedCurrencyCreation(UnsupportedCurrencyCreationException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorMessage(HttpStatus.CONFLICT, ex.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMessage> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorMessage(HttpStatus.CONFLICT, ex.getMessage()));
    }

    @ExceptionHandler(RecordAlreadyExistsException.class)
    public ResponseEntity<ErrorMessage> handleRecordAlreadyExists(RecordAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorMessage(HttpStatus.CONFLICT, ex.getMessage()));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorMessage> handleBadRequest(BadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(HttpStatus.BAD_REQUEST, ex.getMessage()));
    }
}
