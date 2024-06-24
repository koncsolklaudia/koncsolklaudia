package com.example.backendapi.controllers;

import com.example.backendapi.models.*;
import com.example.backendapi.services.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class RestControll {

    private final RestService restService;
    @Autowired
    public RestControll(RestService restService) {
        this.restService = restService;
    }

    @GetMapping("/doubling")
    public ResponseEntity<?> doubling(@RequestParam(required = false) Integer input){
        restService.saveLog(new Log("/doubling", "input: " + input));
        input = 2;
        if(input == 0){
            return ResponseEntity.status(200).body(new ErrorMessage("Provide an input"));
        } else {
            Doubling doubling = new Doubling(input);
            return ResponseEntity.status(200).body(doubling);
        }
    }

    @GetMapping("/greeter")
    public ResponseEntity<?> greeting(@RequestParam(required = false) String name, @RequestParam(required = false) String title){
        restService.saveLog(new Log("/greeter", "name: " + name + ", title: " + title));
        if (name == null && title==null){
            return ResponseEntity.status(400).body(new ErrorMessage("Please provide a name and a title!"));
        } else if (name ==null){
            return ResponseEntity.status(400).body(new ErrorMessage("Please, provide a name"));
        } else if (title ==null){
            return ResponseEntity.status(400).body(new ErrorMessage("Please, provide a title"));
        } else{
            return ResponseEntity.status(200).body(new WelcomeMessage(name,title));
        }
    }

    @PostMapping("/dountil/{action}")
    public ResponseEntity<?> doUntil(@PathVariable String action, @RequestBody DoUntil doUntil){
        restService.saveLog(new Log("/dountil/" + action, "until: " + doUntil.getUntil()));
        if (action.equals("sum") || action.equals("factor")){
            return ResponseEntity.status(200).body(restService.action(doUntil,action));
        } else
            return ResponseEntity.status(400).body(new ErrorMessage("Please, provide an action"));
        }

    @GetMapping(path = "/appenda/{appendable}")
    public ResponseEntity<?> append(@PathVariable String appendable){
        restService.saveLog(new Log("/appenda/" + appendable, "appendable: " + appendable));
        if (appendable == null){
            return ResponseEntity.status(400).body("No appendable words");
        } else {
            AppendA append = new AppendA(appendable);
            return ResponseEntity.status(200).body(append);
        }
    }

    @PostMapping("/arrays")
    public ResponseEntity<?> arrays(@RequestBody Arrays numbers){
        restService.saveLog(new Log("/arrays", "numbers: " + numbers));
        if (numbers == null){
            return ResponseEntity.status(404).body(new ErrorMessage("Please provide what to do with the numbers!"));
        } else if (numbers.getWhat().equals("double")){
            return ResponseEntity.status(200).body(new ResultArray(restService.doubleList(numbers)));
        } else {
            return ResponseEntity.status(200).body(new Result(restService.sumOrMultiply(numbers.getWhat(), numbers)));
        }
    }

    @GetMapping(path = "/log")
    public ResponseEntity<?> log(){
        ResultLog result = new ResultLog(restService.listLogs());
        if (result.getEntries().size() > 0){
            return ResponseEntity.status(200).body(result);
        } else {
            return ResponseEntity.status(404).body(new ErrorMessage("Logs are empty!"));
        }
    }
    }


