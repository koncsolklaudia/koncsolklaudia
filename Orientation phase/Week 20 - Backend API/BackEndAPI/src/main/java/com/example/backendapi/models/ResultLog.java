package com.example.backendapi.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ResultLog {

    private List<Log> entries;
    private int entry_count;

    public ResultLog(List<Log> entries) {
        this.entries = entries;
        this.entry_count = entries.size();
    }
}
