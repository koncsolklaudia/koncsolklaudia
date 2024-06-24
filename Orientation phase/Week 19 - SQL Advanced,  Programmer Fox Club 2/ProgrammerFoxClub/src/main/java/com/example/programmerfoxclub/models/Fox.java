package com.example.programmerfoxclub.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Fox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Food food;
    private Drink drink;
    private List<Trick> tricks = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "fox")
    private User user;

    public Fox(String name) {
        this.name = name;
        this.drink = Drink.MILK;
        this.food = Food.EGGS;
    }

    public void addTrick(Trick trickName) {
        tricks.add(trickName);
    }
}
