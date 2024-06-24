package com.example.com_greenfoxacademy.controllers;

import com.example.com_greenfoxacademy.models.Greeting;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController {

    @RequestMapping("/greeting")
    public Greeting GreetingMethod(){
        return new Greeting("Hello, Klaudia");

    }

    @RequestMapping("/hellothere")
    public Greeting hello(@RequestParam(value = "name", required = true) String name){
        Greeting greeting = new Greeting("Hello" + name);
        return greeting;
    }
}
