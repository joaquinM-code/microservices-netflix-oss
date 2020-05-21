package com.ecatom.productservice.services;

import com.ecatom.productservice.model.Product;

import java.util.List;

public interface ProductServiceInterface {
    List<Product> findAll();
    Product findById(Long id);

}
