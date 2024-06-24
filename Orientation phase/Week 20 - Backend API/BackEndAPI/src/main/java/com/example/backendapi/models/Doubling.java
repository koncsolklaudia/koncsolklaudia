package com.example.backendapi.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doubling {

    private int received;
    private int result;

    public Doubling(int received){
        this.received = received;
        this.result = received *2;
    }

}
