package com.ecommerce.rest.authentication.entry;

import java.io.IOException;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response.Status;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class GenericEntryPoint implements AuthenticationEntryPoint {

    private static final Logger logger = Logger.getLogger(GenericEntryPoint.class.getName());

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        logger.info("Inside commence");

        response.addHeader(HttpHeaders.WWW_AUTHENTICATE, "Bearer realm = " + "realmName");
        response.setContentType(MediaType.APPLICATION_JSON);
        response.setStatus(Status.UNAUTHORIZED.getStatusCode());
    }
}
