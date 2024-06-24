package com.example.programmerfoxclub.service;

import com.example.programmerfoxclub.models.Drink;
import com.example.programmerfoxclub.models.Food;
import com.example.programmerfoxclub.models.Fox;
import com.example.programmerfoxclub.models.Trick;
import com.example.programmerfoxclub.repositories.FoxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FoxServiceImpl implements FoxService {

    private final FoxRepository foxRepository;

    @Override
    public void learnNewTrick(Fox fox, Trick newTrick) {
        if (!fox.getTricks().contains(newTrick)) {
            fox.addTrick(newTrick);
        }
        foxRepository.save(fox);
    }

    @Override
    public List<Trick> unknownTricks(Fox fox) {
        return Arrays.stream(Trick.values())
                .filter(trick -> !fox.getTricks().contains(trick))
                .toList();
    }

    @Override
    public void setNutrition(Fox fox, Food food, Drink drink) {
        fox.setFood(food);
        fox.setDrink(drink);
        foxRepository.save(fox);
    }

    @Override
    public void save(Fox fox) {
        foxRepository.save(fox);
    }
}
