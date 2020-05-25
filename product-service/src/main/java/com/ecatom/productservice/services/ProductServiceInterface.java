package com.ecatom.productservice.services;

import com.ecatom.commons.model.Product;

import java.util.List;

public interface ProductServiceInterface {
    List<Product> findAll();
    Product findById(Long id);
    Product save(Product product);
    void deleteById(Long id);
}
