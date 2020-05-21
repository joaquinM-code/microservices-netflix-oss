package com.ecatom.productservice.controllers;


import com.ecatom.productservice.model.Product;
import com.ecatom.productservice.services.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductServiceInterface productService;
    @Value("${server.port}")
    private Integer port;

    public ProductController(ProductServiceInterface productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public List<Product> listAll(){
        return productService.findAll().stream().map(product -> {
            product.setPort(port);
            return product;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Product listProductById(@PathVariable Long id) throws Exception {

        //Testing hystrix with errors
//        if(true){
//            throw new Exception("Unable to load product");//Simulated error to test Hystrix
//        }

        //Testing hystrix with latency
        //By default Hystrix timeout is 1000ms but can be configured
        Thread.sleep(2000L);



        Product product = productService.findById(id);
        product.setPort(port);
        return product;
    }
}
