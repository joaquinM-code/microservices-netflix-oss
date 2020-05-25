package com.ecatom.itemservice.controllers;

import com.ecatom.commons.model.Product;
import com.ecatom.itemservice.model.Item;
import com.ecatom.itemservice.services.ItemServiceInterface;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RefreshScope //This notation allows to apply live changes in the config server values and reflect them live
//We need to add Actuator to the pom
@RestController
public class ItemController {

    private final ItemServiceInterface itemServiceInterface;
    //Injecting environment to validate the current one in getConfig()
    private final Environment env;

    //Retrieving values from the remote configuration file(Injecting global values)
    @Value("${config.value}")
    private String value;


    //    @Qualifier("itemServiceFeign") alternative way to make a service @Primary
    //    The qualifier takes the name of the desired service with the first letter in lowercase
    //    Use @Service("desiredName") to use in the @Qualifier("desiredName")
    //switch between "feign" and "restTemplate" to select the service
    public ItemController(@Qualifier("feign") ItemServiceInterface itemServiceInterface, Environment env) {
        this.itemServiceInterface = itemServiceInterface;
        this.env = env;
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

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product){
        return itemServiceInterface.saveProduct(product);
    }

    @PutMapping("/edit/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product editProduct(@RequestBody Product product, @PathVariable Long id){
        try{
            return itemServiceInterface.updateProduct(product, id);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND , "Product not found");
        }
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductById(@PathVariable Long id){
        try{
            itemServiceInterface.deleteProductById(id);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND , "Product not found");
        }
    }
    //Proving the use of remote values
    //These values can be declared as global or passed as arguments to the method
    @GetMapping("/get-config")
    public ResponseEntity<?> getConfig(@Value("${server.port}") String port){ //Retrieving values from the remote configuration file(Injecting parameter values)
        Map<String , String> json = new HashMap<>();
        json.put("textValue",value);
        json.put("testPort" , port);
        //Validating the environment to check for dev exclusive properties
        if(env.getActiveProfiles().length > 0 && env.getActiveProfiles()[0].equals("dev")){
            json.put("devValue" , env.getProperty("config.name"));
        }


        return new ResponseEntity<Map<String , String>>(json , HttpStatus.OK);
    }


}
