package com.ecommerce.rest.model.exchange;

import org.springframework.web.multipart.MultipartFile;

public record RegistrationRequest(
    String name,
    String username,
    String email,
    String password,
    MultipartFile profilePic
) {}
