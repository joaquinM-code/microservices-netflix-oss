package com.ecatom.itemservice.clients;

import com.ecatom.itemservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "product-service") /// we have removed the hardcoded url to add Ribbon load balancer  (, url = "http://localhost:8001")
public interface ProductRestClient {

    @GetMapping("/all")
    List<Product> listAll();

    @GetMapping("/{id}")
    Product listProductById(@PathVariable Long id);
}
