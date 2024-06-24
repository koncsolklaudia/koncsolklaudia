package com.example.basicwebshop.services;

import com.example.basicwebshop.exceptions.WSException;
import com.example.basicwebshop.models.ShopItem;
import com.example.basicwebshop.repositories.ShopItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;
@Service
public class ShopItemServiceImpl implements ShopItemService{
    private final ShopItemRepository shopItemRepository;
    private final DecimalFormat averageFormat;
    @Autowired
    public ShopItemServiceImpl(ShopItemRepository shopItemRepository, DecimalFormat averageFormat) {
        this.shopItemRepository = shopItemRepository;
        this.averageFormat = averageFormat;
    }
    @Override
    public List<ShopItem> getAll() {
        return shopItemRepository.getAll();
    }

    @Override
    public List<ShopItem> getAvailable() throws WSException {
        List<ShopItem> result = shopItemRepository.getAll()
                .stream()
                .filter(shopItem -> shopItem.getQuantityOfStock() > 0)
                .toList();
        if (result.isEmpty()){
        throw new WSException("There is no available items");
    }
        return result;
    }
    @Override
    public ShopItem getById(Integer id) throws WSException {
        return shopItemRepository.getOne(id);
    }
    @Override
    public List<ShopItem> sortByPrice() {
        return shopItemRepository.getAll()
                .stream()
                .sorted(Comparator.comparing(ShopItem::getPrice))
                .toList();
    }
    @Override
    public Double getAverageStock() {
        Double average = shopItemRepository.getAll().stream()
                .mapToInt(ShopItem::getQuantityOfStock)
                .average()
                .orElse(0D);
        return Double.valueOf(averageFormat.format(average));
    }

    @Override
    public ShopItem getMostExpensive() throws WSException {
    return sortByPrice().stream().reduce((first,second) -> second).orElseThrow(()-> new WSException("No stock available"));
    }

    @Override
    public void add(ShopItem item) {
        shopItemRepository.save(item);
    }
}
