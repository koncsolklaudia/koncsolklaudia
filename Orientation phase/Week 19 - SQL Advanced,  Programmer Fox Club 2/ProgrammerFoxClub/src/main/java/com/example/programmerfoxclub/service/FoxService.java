package com.example.programmerfoxclub.service;


import com.example.programmerfoxclub.models.Drink;
import com.example.programmerfoxclub.models.Food;
import com.example.programmerfoxclub.models.Fox;
import com.example.programmerfoxclub.models.Trick;

import java.util.List;

public interface FoxService {
    void learnNewTrick(Fox fox, Trick newTrick);
    List<Trick> unknownTricks(Fox fox);
    void setNutrition(Fox fox, Food food, Drink drink);
    void save(Fox fox);

}



