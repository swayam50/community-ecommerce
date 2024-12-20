package com.ecommerce.rest.advice;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.ecommerce.rest.exception.UserDuplicationException;
import com.ecommerce.rest.exception.UserException;
import com.ecommerce.rest.model.exchange.GenericResponse;

import static com.ecommerce.rest.common.Messages.ErrorMessage.USERNAME_PASSWORD_IN_USE;
import static com.ecommerce.rest.common.Keys.MetadataKeys.FIELDS;
import static com.ecommerce.rest.common.Keys.MetadataKeys.USERNAME;
import static com.ecommerce.rest.common.Keys.MetadataKeys.EMAIL;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(value = UserException.class)
    public ResponseEntity<GenericResponse> handleUserException(UserException ex) {
        return new ResponseEntity<>(GenericResponse.errorResponse(ex.getMessage()), HttpStatus.valueOf(ex.getStatusCode()));
    }

    @ExceptionHandler(value = UserDuplicationException.class)
    public ResponseEntity<GenericResponse> handleUserDuplicationException(UserDuplicationException ex) {
        List<String> fields = new LinkedList<>();
        if (ex.isDuplicateEmail())
            fields.add(EMAIL);
        if (ex.isDuplicateUsername())
            fields.add(USERNAME);
        return new ResponseEntity<>(GenericResponse.errorResponse(USERNAME_PASSWORD_IN_USE, Map.of(FIELDS, fields)), HttpStatus.valueOf(ex.getStatusCode()));
    }

}
