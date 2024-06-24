package com.example.backendapi.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class AppendA {
    String appended;

    public AppendA() {
    }

    public AppendA(String appendable) {
        this.appended = appendable + "a";
    }
}
