package com.example.usefulutilities.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class UtilityService implements GenService{

    private List<String> colors;
    private Random random;

    public UtilityService() {
        colors = new ArrayList<>();
        colors.add("red");
        colors.add("blue");
        colors.add("lime");
        colors.add("orange");
        colors.add("magenta");
        random = new Random();
    }

    public String randomColor() {
        return colors.get(random.nextInt(colors.size()));
    }

    public String caesar(String text, Integer number) {
        if(text==null || number== null) {
            return "Give me a text!!!!!!";
        }

        if (number < 0) {
            number = 26 + number;
        }

        String result = "";
        for (int i = 0; i < text.length(); i++) {
            int offset = Character.isUpperCase(text.charAt(i)) ? 'A' : 'a';
            result += (char) (((int) text.charAt(i) + number - offset) % 26 + offset);
        }
        return result;
    }


    @Override
    public String validateEmail(String email) {
        if(email==null){
            return "Enter an email address";
        }
        if(email.indexOf('@')>-1 && email.indexOf('.')>-1){
            return email + " is a valid email address";
        } else {
            return email + " is not a valid email address";
        }
    }
}
