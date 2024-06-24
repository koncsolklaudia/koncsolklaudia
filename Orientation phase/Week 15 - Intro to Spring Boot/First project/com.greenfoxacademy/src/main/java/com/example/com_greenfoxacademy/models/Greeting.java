package com.example.com_greenfoxacademy.models;


import java.util.concurrent.atomic.AtomicLong;

public class Greeting {

    private static AtomicLong idCounter = new AtomicLong();

    Long id;
    String content;

    public Greeting(String content) {
        this.id = idCounter.incrementAndGet();
        this.content = content;
    }

    public Long getId() {
        return id;
    }


    public String getContent() {
        return content;
    }
}

