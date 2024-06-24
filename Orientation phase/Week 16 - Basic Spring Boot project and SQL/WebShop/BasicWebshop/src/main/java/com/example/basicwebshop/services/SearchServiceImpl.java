package com.example.basicwebshop.services;

import com.example.basicwebshop.exceptions.NoItemFoundException;
import com.example.basicwebshop.exceptions.WSException;
import com.example.basicwebshop.models.Search;
import com.example.basicwebshop.models.ShopItem;
import com.example.basicwebshop.repositories.ShopItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SearchServiceImpl implements SearchService{

    private final ShopItemRepository shopItemRepository;

    @Autowired
    public SearchServiceImpl(ShopItemRepository shopItemRepository) {
        this.shopItemRepository = shopItemRepository;
    }
    @Override
    public List<ShopItem> search(Search search) throws WSException {
        List<ShopItem> result = shopItemRepository.getAll()
                .stream()
                .filter(search.getIsFound())
                .toList();

        if (result.isEmpty()) {
            throw new NoItemFoundException("There is no item with this name or description: " + search.getSearched());
        }
        return result;
    }

    }
