package com.example.basicwebshop.services;

import com.example.basicwebshop.exceptions.WSException;
import com.example.basicwebshop.models.Search;
import com.example.basicwebshop.models.ShopItem;


import java.util.List;

public interface SearchService {
    //this method do the search. return us found items(list?)
    List<ShopItem> search(Search search) throws WSException;
}
