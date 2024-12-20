package com.ecommerce.rest.authentication.provider;

import java.util.Collection;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.ecommerce.rest.authentication.token.JWTAuthenticationToken;

@Component
public class JWTAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JWTAuthenticationToken jwtAuthToken = (JWTAuthenticationToken)authentication;

        String username = jwtAuthToken.getPrincipal();
        String password = jwtAuthToken.getCredentials();
        Collection<GrantedAuthority> authorities = jwtAuthToken.getAuthorities();

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (Objects.isNull(userDetails))
            throw new AuthenticationServiceException("User details unavailable for username %s".formatted(username));
        else if (
               userDetails.getUsername().isBlank() || userDetails.getPassword().isBlank()
            || !passwordEncoder.matches(password, userDetails.getPassword())
        )
            throw new BadCredentialsException("Invalid credentials provided for username %s".formatted(username));
        else if (!userDetails.isEnabled())
            throw new DisabledException("User with username %s is disabled".formatted(username));

        return JWTAuthenticationToken.fetchAuthenticatedToken(username, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JWTAuthenticationToken.class.equals(authentication);
    }

}
