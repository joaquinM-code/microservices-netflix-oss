package com.ecatom.itemservice.clients;

import com.ecatom.commons.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "product-service") /// we have removed the hardcoded url to add Ribbon load balancer  (, url = "http://localhost:8001")
public interface ProductRestClient {

    @GetMapping("/all")
    List<Product> listAll();

    @GetMapping("/{id}")
    Product listProductById(@PathVariable Long id);

    @PostMapping("/create")
    Product createProduct(@RequestBody Product product);

    @PutMapping("/edit/{id}")
    Product updateProduct(@RequestBody Product product, @PathVariable Long id);

    @DeleteMapping("/delete/{id}")
    void deleteById(@PathVariable Long id);
}
