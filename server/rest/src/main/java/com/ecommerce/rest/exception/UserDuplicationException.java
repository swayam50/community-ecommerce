package com.ecommerce.rest.exception;

import org.springframework.http.HttpStatus;

public class UserDuplicationException extends UserException {
    private static final Integer STATUS_CODE = HttpStatus.CONFLICT.value();

    public UserDuplicationException(String message) {
        super(STATUS_CODE, message);
    }
}