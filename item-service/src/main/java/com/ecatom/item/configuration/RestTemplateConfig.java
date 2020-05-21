package com.ecatom.item.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {


    //We register a bean to be able to Autowire it in the Service
    @Bean("restClient")//Renames the Bean
    @LoadBalanced//Configures the load  balancer for restTemplate
    public RestTemplate registerRestTemplate(){
        return new RestTemplate();
    }
}
