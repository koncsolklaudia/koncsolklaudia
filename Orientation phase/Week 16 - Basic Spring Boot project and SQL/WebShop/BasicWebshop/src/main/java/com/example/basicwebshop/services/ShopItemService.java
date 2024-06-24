package com.example.basicwebshop.services;

import com.example.basicwebshop.exceptions.WSException;
import com.example.basicwebshop.models.ShopItem;


import java.util.List;

public interface ShopItemService {

    //needed for buttons

    List<ShopItem> getAll();
    List<ShopItem> getAvailable() throws WSException;
    ShopItem getById(Integer id) throws WSException;
    List<ShopItem> sortByPrice();
    Double getAverageStock();
    ShopItem getMostExpensive() throws WSException;
    void add(ShopItem item);

}
