package com.ecommerce.rest.model.exchange;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;
import com.ecommerce.rest.model.value.AuthValidationGroups.RegistrationValidationGroup;
import com.ecommerce.rest.model.value.AuthValidationGroups.LoginValidationGroup;

public record AuthRequest(
        @NotBlank(groups = RegistrationValidationGroup.class)
        String name,

        @NotBlank(groups = {RegistrationValidationGroup.class, LoginValidationGroup.class})
        String username,

        @NotBlank(groups = RegistrationValidationGroup.class)
        @Email
        String email,

        @NotBlank(groups = {RegistrationValidationGroup.class, LoginValidationGroup.class})
        String password,

        @NotNull(groups = RegistrationValidationGroup.class)
        MultipartFile profilePic
) {}
