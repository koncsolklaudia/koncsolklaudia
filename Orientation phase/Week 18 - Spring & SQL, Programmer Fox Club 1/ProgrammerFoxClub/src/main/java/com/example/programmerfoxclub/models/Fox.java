package com.example.programmerfoxclub.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Fox {

    String name;
    private Food food;
    private Drink drink;
    private List<Trick> tricks = new ArrayList<>();

    public Fox(String name) {
        this.name = name;
        this.drink = new Water();
        this.food = new Eggs();
    }

    public void addTrick(Trick trickName) {
        tricks.add(trickName);
    }
}
