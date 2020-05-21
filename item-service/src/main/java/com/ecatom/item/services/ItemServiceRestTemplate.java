package com.ecatom.item.services;

import com.ecatom.item.model.Item;
import com.ecatom.item.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("restTemplate")
public class ItemServiceRestTemplate implements ItemServiceInterface{

    private final RestTemplate restClient;

    public ItemServiceRestTemplate(RestTemplate restClient) {
        this.restClient = restClient;
    }

    @Override
    public List<Item> findAll() {
        //Here we can use the name of the service because is load-balanced
        List<Product> products = Arrays.asList(restClient.getForObject("http://product-service/products/all" , Product[].class));
        return products
                .stream()
                .map(product ->
                    new Item(product , 1)
        ).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer amount) {

        amount = amount != null ? amount : 1;

        Map<String, String> pathVariables = new HashMap<>();
        pathVariables.put("id" , id.toString());

        Product product = restClient.getForObject("http://product-service/products/{id}", Product.class, pathVariables);
        return new Item(product , amount);
    }
}
