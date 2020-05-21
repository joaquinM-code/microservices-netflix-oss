package com.ecatom.productservice.repositories;

import com.ecatom.productservice.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
