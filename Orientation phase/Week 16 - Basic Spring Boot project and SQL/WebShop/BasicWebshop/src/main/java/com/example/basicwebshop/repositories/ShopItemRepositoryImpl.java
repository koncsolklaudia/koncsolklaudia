package com.example.basicwebshop.repositories;

import com.example.basicwebshop.exceptions.NoItemFoundException;
import com.example.basicwebshop.models.ShopItem;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Repository
public class ShopItemRepositoryImpl implements ShopItemRepository{

    private final List<ShopItem> items;

    public ShopItemRepositoryImpl() {
        this.items = new ArrayList<>(Arrays.asList(
                new ShopItem(1,"Coca cola", "0.5L Standard coke", 25D, 0),
                new ShopItem(2,"Wokin","Chicken with fried rice", 119D, 100),
                new ShopItem(3,"Nike shoes", "white and grey", 3445, 33),
                new ShopItem(4, "Dog toy", "donut", 23, 34)
        ));
    }
    @Override
    public List<ShopItem> getAll() {
        return items;
    }

    @Override
    public ShopItem getOne(int id) throws NoItemFoundException {
        return items.stream()
                .filter(item -> item.getId() == id)
                        .findFirst()
                        .orElseThrow(()-> new NoItemFoundException("No item with id" + id + "found."));
    }

    @Override
    public void save(ShopItem item) {
        item.setId(items.size() + 1); //create an id
        items.add(item);
    }
}
