package com.ecommerce.rest.common;

public interface Messages {

    interface ErrorMessage {
        String USERNAME_NOT_FOUND = "User with %s username not found!";
        String USERNAME_PASSWORD_IN_USE = "Username or Email already in use!";
    }

}
