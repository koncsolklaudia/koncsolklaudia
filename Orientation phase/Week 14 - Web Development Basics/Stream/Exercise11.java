import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.swing.RowFilter.Entry;

public class Exercise11{

    public static void main(String[] args) {
        String file = "C:\\klaudia-koncsol\\Orientation phase\\Week 14 - Web Development Basics\\Stream\\random text.txt";
        System.out.println(getWordFrequency(file));
        System.out.println(mostCommonWords(file));
        
    }

    public static Map<String, Long> getWordFrequency(String file){

        try {
            String words = Files.readString(Path.of(file));
            return Arrays.stream(
                words
                .replaceAll("[^a-zA-Z]+", " ")
                .split(" "))
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Map<String,Long> mostCommonWords(String file){
        return Objects.requireNonNull(getWordFrequency(file))
        .entrySet().stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .collect(Collectors.toMap(Map,Entry::getKey, Map,Entry::getValue, (x,y)->x, LinkedHashMap::new));
    }
}