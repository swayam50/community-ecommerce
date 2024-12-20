package com.ecommerce.rest.model.exchange;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.web.multipart.MultipartFile;
import com.ecommerce.rest.model.value.AuthValidationGroups;
import com.ecommerce.rest.model.value.UserRole;

public record AuthRequest(
    @NotBlank(groups = AuthValidationGroups.RegistrationValidationGroup.class)
    String name,

    @NotBlank(groups = {AuthValidationGroups.RegistrationValidationGroup.class, AuthValidationGroups.LoginValidationGroup.class})
    String username,

    @NotBlank(groups = AuthValidationGroups.RegistrationValidationGroup.class)
    @Email(
        regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
        flags = Pattern.Flag.CASE_INSENSITIVE,
        groups = AuthValidationGroups.RegistrationValidationGroup.class
    )
    String email,

    @NotBlank(groups = {AuthValidationGroups.RegistrationValidationGroup.class, AuthValidationGroups.LoginValidationGroup.class})
    String password,

    @NotBlank(groups = {AuthValidationGroups.RegistrationValidationGroup.class, AuthValidationGroups.LoginValidationGroup.class})
    String type,

    @NotNull(groups = AuthValidationGroups.RegistrationValidationGroup.class)
    MultipartFile profilePic
) {

    public String getName() { return name; }

    public String getEmail() { return email; }

    public String getUsername() { return username; }

    public String getPassword() { return password; }

    public UserRole getRole() {
        return UserRole.valueOf(type);
    }

}
