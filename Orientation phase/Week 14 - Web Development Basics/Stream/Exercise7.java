package LambdaStream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Exercise7 {

    public static void main(String[] args) {

        List<String> cities = Arrays.asList("ROME", "LONDON", "NAIROBI", "CALIFORNIA", "ZURICH", "NEW DELHI", "AMSTERDAM", "ABU DHABI", "PARIS");

        List<String> expectedOutput;
        expectedOutput = Arrays.asList("ROME"); // for 'R'
        expectedOutput = Arrays.asList("NAIROBI", "NEW DELHI"); // for 'N'

        //Write a method to return a list of strings which start with
        // the specified character (method parameter) using the Stream API.

        List<String> output = cities.stream()
                .filter( n-> n.startsWith("R"))
                .collect(Collectors.toList());

        System.out.println(output);


    }

}
