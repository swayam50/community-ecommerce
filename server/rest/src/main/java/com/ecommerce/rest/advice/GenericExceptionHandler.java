package com.ecommerce.rest.advice;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.ecommerce.rest.model.exchange.GenericResponse;

@RestControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<GenericResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, Object> data = new HashMap<>();
        for (FieldError error : ex.getFieldErrors())
            data.putIfAbsent(error.getField(), error.getDefaultMessage());
        return new ResponseEntity<>(GenericResponse.errorResponse("Input's provided are incorrect", data), HttpStatus.UNPROCESSABLE_ENTITY);
    }

}