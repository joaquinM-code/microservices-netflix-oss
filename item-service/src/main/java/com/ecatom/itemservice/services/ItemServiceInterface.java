package com.ecatom.itemservice.services;

import com.ecatom.commons.model.Product;
import com.ecatom.itemservice.model.Item;

import java.util.List;

public interface ItemServiceInterface {
    List<Item> findAll();
    Item findById(Long id, Integer amount);
    Product saveProduct(Product product);
    Product updateProduct(Product product , Long id);
    void deleteProductById(Long id);
}
