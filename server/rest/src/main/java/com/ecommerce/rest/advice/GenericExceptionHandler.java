package com.ecommerce.rest.advice;

import java.rmi.ServerException;
import java.util.HashMap;
import java.util.Map;
import jakarta.servlet.ServletException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;
import com.ecommerce.rest.model.exchange.GenericResponse;

@RestControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<GenericResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, Object> data = new HashMap<>();
        for (FieldError error : ex.getFieldErrors())
            data.putIfAbsent(error.getField(), error.getDefaultMessage());
        return new ResponseEntity<>(GenericResponse.errorResponse("Invalid Input Provided", data), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(value = {HttpMediaTypeException.class, HttpRequestMethodNotSupportedException.class})
    public <E extends ErrorResponse> ResponseEntity<GenericResponse> handleHttpException(E ex) {
        return new ResponseEntity<>(GenericResponse.errorResponse(ex.getBody().getTitle(), Map.of("info", ex.getBody().getDetail())), ex.getStatusCode());
    }

}