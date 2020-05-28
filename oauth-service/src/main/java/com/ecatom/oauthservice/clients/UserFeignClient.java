package com.ecatom.oauthservice.clients;

import com.ecatom.usercommons.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="user-service")
public interface UserFeignClient {
    @GetMapping("/users/search/getUser")
    User findByUsername(@RequestParam String name);
}
