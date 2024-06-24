package com.example.foxsanctuary.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewFoxFormDTO {
    private String name;
    private String age;
    private String species;
}
