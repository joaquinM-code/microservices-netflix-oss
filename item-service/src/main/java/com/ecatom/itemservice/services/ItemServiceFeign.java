package com.ecatom.itemservice.services;

import com.ecatom.commons.model.Product;
import com.ecatom.itemservice.clients.ProductRestClient;
import com.ecatom.itemservice.model.Item;
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

    @Override
    public Product saveProduct(Product product) {
        return feignClient.createProduct(product);
    }

    @Override
    public Product updateProduct(Product product, Long id) {
        return feignClient.updateProduct(product, id);
    }

    @Override
    public void deleteProductById(Long id) {
        feignClient.deleteById(id);
    }
}
