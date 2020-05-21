package com.ecatom.itemservice.services;

import com.ecatom.itemservice.model.Item;

import java.util.List;

public interface ItemServiceInterface {
    List<Item> findAll();
    Item findById(Long id, Integer amount);
}
