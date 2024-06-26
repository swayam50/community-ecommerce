package com.ecommerce.rest.resource.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ecommerce.rest.common.Constants.HeaderKeys;
import com.ecommerce.rest.model.exchange.RegistrationRequest;
import com.ecommerce.rest.model.exchange.GenericResponse;
import com.ecommerce.rest.service.AuthService;

@RestController
@RequestMapping(
    path = "/v1/users",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
)
public class UserResource {

    @Autowired
    private AuthService authService;

    @PostMapping(path = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<GenericResponse> registerUser(
        @ModelAttribute
        RegistrationRequest request,
        @RequestHeader(value = HeaderKeys.X_INTERNAL_TOKEN, required = false)
        String internalToken
    ) {
        System.out.println(request + " " + internalToken);
        return null;
    }

    @PostMapping("/login")
    public void loginUser() {

    }

}
