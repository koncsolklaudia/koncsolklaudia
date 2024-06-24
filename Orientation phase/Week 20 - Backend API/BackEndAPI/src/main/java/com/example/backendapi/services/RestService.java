package com.example.backendapi.services;
import com.example.backendapi.models.Arrays;
import com.example.backendapi.models.DoUntil;
import com.example.backendapi.models.Log;
import com.example.backendapi.repositories.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestService {

    private final LogRepository logRepository;

    //arrays-models
    public int[] doubleList(Arrays numbers){
        return numbers.doubleNumbers();
    }

    public int action(DoUntil until, String action) {
        int result = 0;
        if (action.equals("sum")) {
            result = until.sum();
        } else if (action.equals("factor")) {
            result = until.factor();
        }
        return result;
    }

    public int sumOrMultiply(String what, Arrays numbers){
        int result = 0;
        if (what.equals("sum")){
            result = numbers.sum();
        } else if (what.equals("multiply")){
            result = numbers.multiply();
        }
        return result;
    }

    public void saveLog(Log log) {
        logRepository.save(log);
    }

    public List<Log> listLogs() {
        return (List<Log>) logRepository.findAll();
    }

    public int logCount(){
        return (int) logRepository.count();
    }
}

