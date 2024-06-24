package LambdaStream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Exercise3 {

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(3, 9, 2, 8, 6, 5);

        List<Integer> expectedOutput = Arrays.asList(9, 8, 6, 5);

        //Write a method to return a list of numbers whose
        // square is greater than 20 from the list of
        // integers below using the Stream API. The value 20 should be one
        // of the method's parameters.

        List<Integer> output = numbers.stream()
                .filter(n-> (int)Math.pow(n,2) > 20)
                .collect(Collectors.toList());
        System.out.println(expectedOutput);
        System.out.println(output);

    }
}
