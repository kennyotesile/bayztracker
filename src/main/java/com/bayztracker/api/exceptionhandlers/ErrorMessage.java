package com.bayztracker.api.exceptionhandlers;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class ErrorMessage {

    private Date timestamp;
    private HttpStatus status;
    private int statusCode;
    private String message;

    private ErrorMessage() {
        timestamp = new Date();
    }

    public ErrorMessage(HttpStatus status) {
        this();
        this.status = status;
        this.statusCode = status.value();
        this.message = "Unexpected error";
    }

    public ErrorMessage(HttpStatus status, String message) {
        this();
        this.status = status;
        this.statusCode = status.value();
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
