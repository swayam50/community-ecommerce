package com.ecommerce.rest.filter;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.ecommerce.rest.authentication.token.JWTAuthenticationToken;
import com.ecommerce.rest.model.value.UserRole;

import static com.ecommerce.rest.common.Constants.BEARER_TOKEN_PREFIX;

@Component
public class AuthFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.info("Inside doFilterInternal");

        String authToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (Objects.isNull(authToken) || authToken.isBlank() || !authToken.startsWith(BEARER_TOKEN_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        logger.info("Passes first check in doFilterInternal");

        String jwtToken = authToken.substring(BEARER_TOKEN_PREFIX.length());
        // validate jwtToken

        String username = "randomusername"; // userDetails.getUsername();
        String password = "randompassword"; // userDetails.getPassword();
        GrantedAuthority authority = UserRole.CUSTOMER;

        JWTAuthenticationToken token = new JWTAuthenticationToken(username, password, Collections.singleton(authority));
        token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(token);

        filterChain.doFilter(request, response);
    }

    private String ensureAuthorization(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        return null;
    }
}
