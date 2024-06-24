package com.example.programmerfoxclub.service;

import com.example.programmerfoxclub.models.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Getter
@Setter
public class FoxService {

    private List<Fox> foxes;
    private Fox selectedFox;
    private Fox defaultFox;
    private List<Trick> tricks = new ArrayList<>();

    public FoxService() {
        this.foxes = new ArrayList<>(Arrays.asList(
                new Fox("Mr.Green"),
                new Fox("Karak")
        ));
        this.defaultFox = findByName("Mr.Green");
        this.selectedFox = defaultFox;
    }

    public Fox findByName(String name) {
        return foxes.stream()
                .filter(fox -> fox.getName().toLowerCase().equals(name.toLowerCase()))
                .findAny()
                .orElse(null);
    }

    public Fox addNewFox(Fox myFox) {
        if (myFox == null) {
            return defaultFox;
        } else if (!foxes.stream().anyMatch(fox -> fox.equals(myFox))) {
            foxes.add(myFox);
        }
        return findByName(myFox.getName());
    }

    public void learnNewTrick(Fox fox, Trick newTrick) {
        if (!fox.getTricks().stream().anyMatch(trick -> trick.equals(newTrick))) {
            fox.addTrick(newTrick);
        }
    }

    public void setNutrition(String food, String drink, Fox fox) {
        Fox myFox = findByName(fox.getName());
        switch (food) {
            case "eggs" -> myFox.setFood(new Eggs());
            case "meatball" -> myFox.setFood(new MeatBall());
        }
        switch (drink) {
            case "milk" -> myFox.setDrink(new Milk());
            case "water" -> myFox.setDrink(new Water());
        }
    }
}



