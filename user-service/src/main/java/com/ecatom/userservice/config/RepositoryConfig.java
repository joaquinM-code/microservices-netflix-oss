package com.ecatom.userservice.config;

import com.ecatom.usercommons.model.Role;
import com.ecatom.usercommons.model.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

//Additional configuration for @RepositoryRestResource
@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        //Exposing id for users and roles
        config.exposeIdsFor(Role.class, User.class);
    }
}
