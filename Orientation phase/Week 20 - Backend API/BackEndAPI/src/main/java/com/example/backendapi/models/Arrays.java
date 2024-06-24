package com.example.backendapi.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Arrays {

    private int[] numbers;
    private String what;

    public int[] doubleNumbers(){
        int[] result = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            result[i] = numbers [i] *2;
        }
        return result;
    }

    public int sum() {
        int result = 0;
        for (int i = 0; i < numbers.length; i++) {
            result += getNumbers()[i];
        }
        return result;
    }

    public int multiply() {
        int result = 1;
        for (int i = 0; i < numbers.length; i++) {
            result *= getNumbers()[i];
        }
        return result;
    }
}
