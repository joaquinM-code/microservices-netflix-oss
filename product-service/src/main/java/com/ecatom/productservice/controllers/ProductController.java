package com.ecatom.productservice.controllers;


import com.ecatom.commons.model.Product;
import com.ecatom.productservice.services.ProductServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
//@RequestMapping("/products") not necessary mapped with Zuul
public class ProductController {
    private final ProductServiceInterface productService;
    public ProductController(ProductServiceInterface productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public List<Product> listAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product listProductById(@PathVariable Long id) throws Exception {

        //Testing hystrix with errors
//        if(true){
//            throw new Exception("Unable to load product");//Simulated error to test Hystrix
//        }

        //Testing hystrix and Zuul with latency
        //By default Hystrix timeout is 1000ms but can be configured
//        Thread.sleep(2000L);

        return productService.findById(id);

    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product){
        return productService.save(product);
    }

    @PutMapping("edit/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Product editProduct(@RequestBody Product product , @PathVariable Long id){
        Product productToEdit = productService.findById(id);
        if(productToEdit != null){
            productToEdit.setName(product.getName());
            productToEdit.setPrice(product.getPrice());
            return productService.save(productToEdit);
        }else{
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Product not found");
        }

    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProductById(@PathVariable Long id){
        try{
            productService.deleteById(id);
        }catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Product not found");
        }

    }
}
