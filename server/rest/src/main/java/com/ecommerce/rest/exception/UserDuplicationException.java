package com.ecommerce.rest.exception;

import org.springframework.http.HttpStatus;

public class UserDuplicationException extends UserException {
    private static final Integer STATUS_CODE = HttpStatus.CONFLICT.value();

    private Boolean duplicateUsername, duplicateEmail;

    public UserDuplicationException(String message, Boolean duplicateUsername, Boolean duplicateEmail) {
        super(STATUS_CODE, message);
        this.duplicateUsername = duplicateUsername;
        this.duplicateEmail = duplicateEmail;
    }

    public Boolean isDuplicateUsername() {
        return duplicateUsername;
    }

    public Boolean isDuplicateEmail() {
        return duplicateEmail;
    }
}