package com.example.basicwebshop.models;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Search {
    private String searched;

    public Search(String searched){
        this.searched = searched.trim().toLowerCase();
    }
    //it doesnt matter the user write(capital/lowercase) it always be used lowercase letter

    private Predicate<ShopItem> isFound =
        item -> item.getDescription().toLowerCase().contains(searched)
                || item.getName().toLowerCase().contains(searched);
    //predicate is a method - can turn true or false
}
