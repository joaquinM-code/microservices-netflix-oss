package com.ecatom.zuulserver.oauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //Configuring access rules
        http.authorizeRequests()
                .antMatchers(
                        "/api/security/oauth/**")
                        .permitAll() //Allow public access to the authentication route
                .antMatchers(
                        HttpMethod.GET, "/api/product/all" , "/api/item/all" , "/api/user/user")
                        .permitAll()  //Allow public access to all product and item list;
                .antMatchers(
                        HttpMethod.GET, "/api/product/{id}" , "/api/item/{id}/{amount}" , "/api/user/user/{id}")
                        .hasAnyRole("ADMIN" , "USER") //Allow access to specific routes only to authenticated users and admins
                .antMatchers("/api/product/**", "/api/item/**" , "/api/user/**").hasRole("ADMIN") //CRUD ONLY FOR ADMIN
                .anyRequest().denyAll();
    }

    //TOKEN CONVERTERS TO VALIDATE THE TOKENS
    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey("move_this_key_to_config_server");
        return jwtAccessTokenConverter;
    }
}
