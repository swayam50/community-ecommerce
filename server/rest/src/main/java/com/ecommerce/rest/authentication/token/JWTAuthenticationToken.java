package com.ecommerce.rest.authentication.token;

import java.util.Collection;
import java.util.Objects;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class JWTAuthenticationToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = 69420L;

    private String principle;
    private String credentials;

    public JWTAuthenticationToken(String principle, String credentials, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);

        this.principle = principle;
        this.credentials = credentials;

        super.setAuthenticated(Objects.nonNull(authorities));
    }

    public static JWTAuthenticationToken fetchUnauthenticatedToken(String principal, String credentials) {
        return new JWTAuthenticationToken(principal, credentials);
    }

    public static JWTAuthenticationToken fetchAuthenticatedToken(String principal, String credentials, Collection<? extends GrantedAuthority> authorities) {
        return new JWTAuthenticationToken(principal, credentials, authorities);
    }

    public JWTAuthenticationToken(String principle, String credentials) {
        this(principle, credentials, null);
    }

    @Override
    public String getPrincipal() {
        return principle;
    }

    @Override
    public String getCredentials() {
        return credentials;
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.credentials = null;
    }
}
