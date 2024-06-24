package com.example.backendapi.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoUntil {

    private int until;

    public int sum(){
        int result=0;
        for (int i = 0; i < until; i++) {
            result+=i;
        }
        return result;
    }

    public int factor(){
        int result=1;
        for (int i = 0; i < until; i++) {
            result *=i;
        }
        return result;
    }
}
