package com.example.programmerfoxclub.controllers;

import com.example.programmerfoxclub.models.Fox;
import com.example.programmerfoxclub.models.Trick;
import com.example.programmerfoxclub.service.FoxService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final FoxService foxService;

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("fox", foxService.addNewFox(foxService.getSelectedFox()));
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("fox", new Fox("foxy"));
        return "login";
    }

    @PostMapping("/login")
    public String myFox(@ModelAttribute("fox") Fox fox) {
        foxService.setSelectedFox(fox);
        return "redirect:/";
    }
    @GetMapping("/trick-center")
    public String selectNewTrick(Model model) {
        model.addAttribute("tricks", Trick.values());
        return "tricks";
    }

    @PostMapping("/trick-center")
    public String addNewTrick(@ModelAttribute("tricks") Trick trick) {
        foxService.learnNewTrick(foxService.getSelectedFox(), trick);
        return "redirect:/";
    }

    @GetMapping("/nutritioncenter")
    public String chooseNutrition(Model model) {
        String food = "";
        String drink = "";
        model.addAttribute("newFood", food);
        model.addAttribute("newDrink", drink);
        return "nutrition";
    }

    @PostMapping("/nutritioncenter")
    public String changeNutrition(@ModelAttribute("newFood") String food, @ModelAttribute("newDrink") String drink) {
        foxService.setNutrition(food, drink, foxService.getSelectedFox());
        return "redirect:/";
    }


}
