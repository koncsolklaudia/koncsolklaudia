package com.example.programmerfoxclub.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum Drink {
    MILK ("milk"),
    WATER ("water"),
    WINE ("wine");

    private final String name;

    Drink(String name) {
        this.name = name;
    }
}