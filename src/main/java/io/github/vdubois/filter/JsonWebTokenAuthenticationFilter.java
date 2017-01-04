package io.github.vdubois.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;

/**
 * Created by vdubois on 30/12/16.
 */
public class JsonWebTokenAuthenticationFilter extends RequestHeaderAuthenticationFilter {

    public JsonWebTokenAuthenticationFilter() {
        // Don't throw exceptions if the header is missing
        this.setExceptionIfHeaderMissing(false);

        // This is the request header it will look for
        this.setPrincipalRequestHeader("Authorization");
    }

    @Override
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }
}
