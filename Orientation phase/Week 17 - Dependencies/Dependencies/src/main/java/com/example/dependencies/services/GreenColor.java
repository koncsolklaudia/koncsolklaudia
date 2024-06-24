package com.example.dependencies.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class GreenColor implements MyColor{

    private Printer printer;

    @Autowired
    GreenColor(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void printColor() {
        printer.log("green");
    }
}
