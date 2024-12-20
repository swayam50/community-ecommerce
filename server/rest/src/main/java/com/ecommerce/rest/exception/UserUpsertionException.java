package com.ecommerce.rest.exception;

import org.springframework.http.HttpStatus;

public class UserUpsertionException extends UserException {
    private static final Integer STATUS_CODE = HttpStatus.INTERNAL_SERVER_ERROR.value();

    public UserUpsertionException(String message) {
        super(STATUS_CODE, message);
    }

}
