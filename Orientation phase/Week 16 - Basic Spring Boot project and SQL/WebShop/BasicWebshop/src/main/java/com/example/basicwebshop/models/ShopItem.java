package com.example.basicwebshop.models;

import lombok.*;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Component
public class ShopItem {
    private Integer id;
    private String name;
    private String description;
    private double price;
    private Integer quantityOfStock;
}

