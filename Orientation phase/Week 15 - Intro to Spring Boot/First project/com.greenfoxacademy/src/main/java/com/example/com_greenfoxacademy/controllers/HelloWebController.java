package com.example.com_greenfoxacademy.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class HelloWebController {

    public static AtomicInteger counter = new AtomicInteger();
    @RequestMapping("/web/greeting")
    public String greeting(Model model, @RequestParam(value = "name") String name){
      model.addAttribute("name",name);
      model.addAttribute("counter", counter.incrementAndGet());
        return "greeting";
    }

    @RequestMapping("/web/greetingworld")
    public String greetingWorld(Model model){
        String[] hellos = {"Mirëdita", "Ahalan", "Parev", "Zdravei", "Nei Ho", "Dobrý den", "Ahoj", "Goddag", "Goede dag, Hallo", "Hello", "Saluton", "Hei", "Bonjour",
                "Guten Tag", "Gia'sou", "Aloha", "Shalom", "Namaste", "Namaste", "Jó napot", "Halló", "Helló", "Góðan daginn", "Halo", "Aksunai", "Qanuipit", "Dia dhuit",
                "Salve", "Ciao", "Kon-nichiwa", "An-nyong Ha-se-yo", "Salvëte", "Ni hao", "Dzien' dobry", "Olá", "Bunã ziua", "Zdravstvuyte", "Hola", "Jambo", "Hujambo", "Hej",
                "Sa-wat-dee", "Merhaba", "Selam", "Vitayu", "Xin chào", "Hylo", "Sut Mae", "Sholem Aleychem", "Sawubona"};
    Random rnd = new Random();
    model.addAttribute("hello", hellos[rnd.nextInt(hellos.length)]);
    return "greetingworld";
    }

}
