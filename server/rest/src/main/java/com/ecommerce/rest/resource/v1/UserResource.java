package com.ecommerce.rest.resource.v1;

import java.net.URI;
import java.util.Map;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.ecommerce.rest.common.Keys.HeaderKeys;
import com.ecommerce.rest.model.data.UserData;
import com.ecommerce.rest.model.exchange.AuthRequest;
import com.ecommerce.rest.model.exchange.GenericResponse;
import com.ecommerce.rest.model.value.AuthValidationGroups.RegistrationValidationGroup;
import com.ecommerce.rest.model.value.AuthValidationGroups.LoginValidationGroup;
import com.ecommerce.rest.model.value.UserRole;
import com.ecommerce.rest.service.UserService;
import org.modelmapper.ModelMapper;

@RestController
@RequestMapping(
        path = "/v1/users",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class UserResource {

    private static final Logger logger = Logger.getLogger(UserResource.class.getName());

    @Value("${app.internal.token}")
    private String internalToken;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @PostMapping(path = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<GenericResponse> registerUser(
            @ModelAttribute
            @Validated(RegistrationValidationGroup.class)
            AuthRequest request,
            @RequestHeader(value = HeaderKeys.X_INTERNAL_TOKEN, required = false)
            String appToken,
            @Autowired
            ServletUriComponentsBuilder uriComponentsBuilder
    ) {
        logger.info("User registration request received with request: " + request + " and internalToken: " + appToken);

        UserData userData = modelMapper.map(request, UserData.class);
        userData.setRole(request.getRole());

        if (UserRole.SYSTEM.equals(request.getRole()) && !internalToken.equals(appToken))
            return new ResponseEntity<>(GenericResponse.errorResponse("Invalid role specified!"), HttpStatus.UNPROCESSABLE_ENTITY);

        userData = userService.registerUser(userData);

        // TODO:: add HATEOAS using WebMvcLinkBuilder
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                                             .path("/users/{userId}")
                                             .build(userData.getUserId());

        return ResponseEntity.created(uri).body(GenericResponse.successResponse("User saved successfully.", Map.of("user", userData)));
    }

    @PostMapping("/login")
    public ResponseEntity<GenericResponse> loginUser(
            @RequestBody
            @Validated(LoginValidationGroup.class)
            AuthRequest request
    ) {
        logger.info("User login request received with request: " + request);

        UserData userData = modelMapper.map(request, UserData.class);
        userData = userService.loginUser(userData);

        return ResponseEntity.ok().build();
    }

}
