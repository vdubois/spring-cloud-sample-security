package io.github.vdubois.security.config;

import io.github.vdubois.security.filter.JsonWebTokenAuthenticationFilter;
import io.github.vdubois.security.service.JsonWebTokenAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by vdubois on 30/12/16.
 */
public abstract class JsonWebTokenSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private JsonWebTokenAuthenticationProvider authenticationProvider;

    @Bean
    public JsonWebTokenAuthenticationFilter jsonWebTokenAuthenticationFilter() {
        return new JsonWebTokenAuthenticationFilter();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // This method is here with the @Bean annotation so that Spring can
        // autowire it
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // disable CSRF, http basic, form login
                .csrf().disable() //
                .httpBasic().disable() //
                .formLogin().disable()

                // ReST is stateless, no sessions
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //

                .and()

                // return 403 when not authenticated
                .exceptionHandling().authenticationEntryPoint(new Http403ForbiddenEntryPoint());

        // Let child classes set up authorization paths
        setupAuthorization(http);

        http.addFilterBefore(jsonWebTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    protected abstract void setupAuthorization(HttpSecurity http) throws Exception;
}
