package LambdaStream;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Fox {

    String name;
    String color;
    int age;

    public Fox(String name, String color, int age) {
        this.name = name;
        this.color = color;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Fox{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args) {

        List<Fox> listOfFoxes = Arrays.asList(
        new Fox("Fox1","Red",2),
        new Fox("Fox2","Red",4),
        new Fox("Fox3","Red",12),
        new Fox("Fox4","Red",23),
        new Fox("Fox5","Yellow",22),
        new Fox("Fox6","Yellow",6),
        new Fox("Fox7","Green",2),
        new Fox("Fox8","Green",8));

        System.out.println("The green Foxes listed here: " + greenFoxes(listOfFoxes));
        System.out.println("Green and younger than 5: " + greenAndYoung(listOfFoxes));
        System.out.println("Occurence of Colors: " + occurenceOfColors(listOfFoxes));
    }


    //write a method to return a list of foxes of green color

    public static  List<Fox> greenFoxes(List<Fox> listOfFoxes){
        return listOfFoxes.stream()
                .filter(f->f.getColor().equals("Green"))
                .collect(Collectors.toList());
    }


   // write a method to return a list of foxes of green color younger than 5

    public static List<Fox> greenAndYoung(List<Fox> listOfFoxes){
        return listOfFoxes.stream()
                .filter(f->f.getColor().equals("Green"))
                .filter(f->f.getAge()<5)
                .collect(Collectors.toList());
    }

    //write a method to return a map specifying the frequency of foxes by color

    public static Map<String,Long> occurenceOfColors(List<Fox> listOfFoxes){
        return listOfFoxes.stream()
                .collect(Collectors.groupingBy(f->f.getColor(), Collectors.counting()));
    }

    }

