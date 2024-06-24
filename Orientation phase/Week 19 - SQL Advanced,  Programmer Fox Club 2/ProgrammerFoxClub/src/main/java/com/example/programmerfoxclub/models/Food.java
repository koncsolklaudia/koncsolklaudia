package com.example.programmerfoxclub.models;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
public enum Food {
    EGGS("eggs"),
    LIVER("liver"),
    MEATBALL("meatball");

    private final String name;

    Food(String name) {
        this.name = name;
    }
}