package io.github.vdubois.model;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by vdubois on 30/12/16.
 */
public class JsonWebTokenAuthentication extends AbstractAuthenticationToken {

    private UserDetails principal;
    private String jsonWebToken;

    public JsonWebTokenAuthentication(UserDetails principal, String jsonWebToken) {
        super(principal.getAuthorities());
        this.principal = principal;
        this.jsonWebToken = jsonWebToken;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    public String getJsonWebToken() {
        return jsonWebToken;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
