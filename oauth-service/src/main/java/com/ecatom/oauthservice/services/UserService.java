package com.ecatom.oauthservice.services;

import com.ecatom.oauthservice.clients.UserFeignClient;
import com.ecatom.usercommons.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService , UserServiceInterface {

    private final UserFeignClient client;
    private Logger log = LoggerFactory.getLogger(UserService.class);

    public UserService(UserFeignClient client) {
        this.client = client;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //Get user using feign
        User user = client.findByUsername(userName);
        //Validating user exists
        if(user == null){
            log.error("User "+userName+" tried to login");
            throw new UsernameNotFoundException("User not found");
        }

        //Converting user roles to granted authorities
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .peek(authority -> log.info("Role: "+authority.getAuthority()))
                .collect(Collectors.toList());
        //Logging authenticated user
        log.info("User: "+user.getUserName());

        //Returning UserDetails ready to be used with the authentication manager
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                user.getEnabled(),
                true,
                true,
                true,
                authorities);
    }

    //Fetch user details from user-service
    @Override
    public User findByUserName(String userName) {
        return client.findByUsername(userName);
    }
}
