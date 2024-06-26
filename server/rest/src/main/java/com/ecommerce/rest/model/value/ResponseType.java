package com.ecommerce.rest.model.value;

public enum ResponseType {
    SUCCESS("success"), ERROR("error");

    private String type;

    private ResponseType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
