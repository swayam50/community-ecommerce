package com.ecommerce.rest.model.value;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    SYSTEM, MERCHANT, CUSTOMER;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
