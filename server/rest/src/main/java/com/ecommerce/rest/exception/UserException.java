package com.ecommerce.rest.exception;

public abstract class UserException extends RuntimeException {
    private final Integer statusCode;

    protected UserException(Integer statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}