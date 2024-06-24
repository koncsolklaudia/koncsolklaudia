package com.example.com_greenfoxacademy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

    @RequestMapping(value="/hello")
    @ResponseBody
    public String HelloMethod(){
        return "Helloooo";
    }

    }


