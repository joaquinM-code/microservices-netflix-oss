package com.ecatom.itemservice.controllers;

import com.ecatom.itemservice.model.Item;
import com.ecatom.itemservice.model.Product;
import com.ecatom.itemservice.services.ItemServiceInterface;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {

//    @Qualifier("itemServiceFeign") alternative way to make a service @Primary
//    The qualifier takes the name of the desired service with the first letter in lowercase
//    Use @Service("desiredName") to use in the @Qualifier("desiredName")
    private final ItemServiceInterface itemServiceInterface;

    //switch between "feign" and "restTemplate" to select the service
    public ItemController(@Qualifier("feign") ItemServiceInterface itemServiceInterface) {
        this.itemServiceInterface = itemServiceInterface;
    }


    @GetMapping("/all")
    public List<Item> getAllItems() {
        return itemServiceInterface.findAll();
    }

    //Hystrix configuration
    @HystrixCommand(fallbackMethod = "findByIdProductControllerError")
    @GetMapping(value = {"/{id}/{amount}" ,"/{id}"})
    public Item getItemById(@PathVariable(value = "id") Long id, @PathVariable(required = false , value = "amount") Integer amount) {
        return itemServiceInterface.findById(id, amount);
    }

    //Hystrix fallback method in case of error
    public Item findByIdProductControllerError(Long id, Integer amount){
        amount = amount != null ? amount : 1;
        Product fallbackProduct = new Product();
        fallbackProduct.setId(id);
        fallbackProduct.setName("Default recommended product");
        fallbackProduct.setPrice(7.0);

        return new Item(fallbackProduct , amount);
    }
}
