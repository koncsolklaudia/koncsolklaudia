package com.example.basicwebshop.repositories;

import com.example.basicwebshop.exceptions.NoItemFoundException;
import com.example.basicwebshop.models.ShopItem;
import org.springframework.stereotype.Component;

import java.util.List;

public interface ShopItemRepository {
    //in memory repository instead of mySql
    List<ShopItem> getAll();
    ShopItem getOne(int id) throws NoItemFoundException;
    void save(ShopItem item);


}
