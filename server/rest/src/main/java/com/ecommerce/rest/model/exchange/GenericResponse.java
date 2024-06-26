package com.ecommerce.rest.model.exchange;

import java.util.Map;
import com.ecommerce.rest.model.value.ResponseType;

public record GenericResponse(String status, String message, Map<String, Object> data) {
    public static GenericResponse successResponse(String message, Map<String, Object> data) {
        return new GenericResponse(ResponseType.SUCCESS.getType(), message, data);
    }

    public static GenericResponse errorResponse(String message, Map<String, Object> data) {
        return new GenericResponse(ResponseType.ERROR.getType(), message, data);
    }
}