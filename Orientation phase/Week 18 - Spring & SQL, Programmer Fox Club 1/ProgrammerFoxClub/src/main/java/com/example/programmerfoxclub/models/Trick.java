package com.example.programmerfoxclub.models;

import lombok.Getter;

@Getter
public enum Trick {
    BACKFLIP ("backflip"),
    DANCE ("dance"),
    STARE ("stare"),
    YAWN ("yawn");

    private String name;
    Trick(String name) {
        this.name = name;
    }
}
