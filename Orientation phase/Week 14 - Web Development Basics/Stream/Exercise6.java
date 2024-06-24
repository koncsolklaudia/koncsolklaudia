package LambdaStream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Character.isUpperCase;

public class Exercise6 {

    public static void main(String[] args) {


        String s = "Lorem Ipsum Dolor Sit Amet, Consectetur Adipiscing Elit. Morbi nec mattis odio.";

        List<Character> expectedOutput = Arrays.asList('L', 'I', 'D', 'S', 'A', 'C', 'A', 'E', 'M');

        //Write a method to return a
        // list of uppercase characters from a given string using the Stream API.


        List<Character> upperCaseChars = s.chars()
                .filter( c -> isUpperCase(c))
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());

        System.out.println(expectedOutput);
        System.out.println(upperCaseChars);

    }
}
