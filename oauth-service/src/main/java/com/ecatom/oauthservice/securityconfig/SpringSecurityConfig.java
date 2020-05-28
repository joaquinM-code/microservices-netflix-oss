package com.ecatom.oauthservice.securityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userService;

    public SpringSecurityConfig(UserDetailsService userService) {
        this.userService = userService;
    }

    //PASSWORD ENCODER needed to build the AuthenticationManager
    @Bean//Bean is the equivalent to @Component but for methods (Registers the component in the spring context)
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //Builds the Authentication manager with the user provided details and hashes the provided password to be compared with the DB
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Override
    @Bean //Exports the AuthenticationManager to be used in AuthorizationServerConfig
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }


}
