package LambdaStream;

import java.util.Arrays;
import java.util.List;

public class Exercise8 {

    public static void main(String[] args) {

        List<Character> characters = Arrays.asList('L', 'o', 'r', 'e', 'm', ' ', 'i', 'p', 's', 'u', 'm');

        String expectedOutput = "Lorem ipsum";


       // Write a method to return a string concatenated from a
        // given list of characters using the Stream API.

       String concatenatedString = characters.stream()
               .map(c->c.toString())
               .reduce("",String::concat);

        System.out.println(expectedOutput);
        System.out.println(concatenatedString);
    }
}
