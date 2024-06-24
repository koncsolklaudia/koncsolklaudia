package LambdaStream;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Exercise9 {

    public static void main(String[] args) {

        String s = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt.";


        //Write a method to return a map specifying
        // the frequency of characters in a given string using the Stream API

        System.out.println(frequencyOfChars(s));

    }
        public static Map<String, Long> frequencyOfChars(String s){
        return Arrays.stream(s.split(""))
                .collect(Collectors.groupingBy(n -> n, Collectors.counting()));
        }

    }

