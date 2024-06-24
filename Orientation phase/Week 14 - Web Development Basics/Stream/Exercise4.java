package LambdaStream;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;


public class Exercise4 {

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, 3, -2, -4, -7, -3, -8, 12, 19, 6, 9, 10, 14);

        double expectedOutput = 22.0/6.0;

        //Write a method to return the average of odd páratlan numbers in the list of integers
        // below using the Stream API. Implement this without calculating the sum first!

        OptionalDouble output = numbers.stream()
                .filter(n-> n % 2 != 0 )
                .mapToInt(n->n)
                .average();

                if (output.isPresent()){
                    System.out.println("The average is " + output.getAsDouble());
                } else {
                    System.out.println("Empty list");
                }
    }
}
