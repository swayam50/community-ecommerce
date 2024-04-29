package com.ecommerce.rest.resource.v1;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ecommerce.rest.common.Constants.HeaderKeys;
import com.ecommerce.rest.model.request.AuthRequest;

@RestController
@RequestMapping(
    path = "/v1/auth",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
)
public class AuthResource {

    @PostMapping("/register")
    public void registerUser(
        @RequestBody
        AuthRequest request,
        @RequestHeader(value = HeaderKeys.X_INTERNAL_TOKEN, required = false)
        String internalToken
    ) {
        System.out.println(request + " " + internalToken);
    }

    @PostMapping("/login")
    public void loginUser() {

    }

}
