package LambdaStream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Exercise2 {

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, 3, -2, -4, -7, -3, -8, 12, 19, 6, 9, 10, 14);

        List<Integer> expectedOutput = Arrays.asList(1, 9, 144, 361, 36, 81, 100, 196);

        //Write a method to return a list of
        // squares of positive numbers from the list of integers below using the Stream API.

        List<Integer> positiveNumbers = numbers.stream()
                .filter(n-> n>0)
                .map( n-> (int)Math.pow(n,2)) //The map method is used to map each element to its corresponding result
                .collect(Collectors.toList());
        System.out.println(expectedOutput);
        System.out.println(positiveNumbers);
    }
}
