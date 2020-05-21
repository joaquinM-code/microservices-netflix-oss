package com.ecatom.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


//@RibbonClient(name = "product-service")//Con be used in plural to add multiple clients
//When we enable Eureka ribbon is not necessary
@EnableCircuitBreaker //Wraps ribbon and takes care of errors using preconfigured fallback methods
@EnableEurekaClient //Not necessary already enabled with the pom dependency
@EnableFeignClients
@SpringBootApplication
public class ItemServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItemServiceApplication.class, args);
    }

}
