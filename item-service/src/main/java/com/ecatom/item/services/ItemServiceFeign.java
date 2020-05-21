package com.ecatom.item.services;

import com.ecatom.item.clients.ProductRestClient;
import com.ecatom.item.model.Item;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("feign")
public class ItemServiceFeign implements ItemServiceInterface {

    private final ProductRestClient feignClient;

    public ItemServiceFeign(ProductRestClient feignClient) {
        this.feignClient = feignClient;
    }


    @Override
    public List<Item> findAll() {
        return feignClient.listAll().stream().map(product -> new Item(product, 2)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer amount) {
        amount = amount != null ? amount : 1;
        return new Item(feignClient.listProductById(id), amount);
    }
}
