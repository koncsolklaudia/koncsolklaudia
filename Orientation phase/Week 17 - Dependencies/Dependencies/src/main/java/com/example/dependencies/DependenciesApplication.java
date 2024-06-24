package com.example.dependencies;

import com.example.dependencies.services.MyColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DependenciesApplication implements CommandLineRunner {

    private MyColor myColor;

    @Autowired
    public DependenciesApplication(@Qualifier("blueColor") MyColor myColor) {
        this.myColor = myColor;
    }

    public static void main(String[] args) {
        SpringApplication.run(DependenciesApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        myColor.printColor();
    }
}
