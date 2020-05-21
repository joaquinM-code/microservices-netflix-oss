package com.ecatom.item.services;

import com.ecatom.item.model.Item;

import java.util.List;

public interface ItemServiceInterface {
    List<Item> findAll();
    Item findById(Long id, Integer amount);
}
