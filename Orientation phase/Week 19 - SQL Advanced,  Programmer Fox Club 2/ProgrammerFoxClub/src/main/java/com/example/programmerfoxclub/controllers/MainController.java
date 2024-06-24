package com.example.programmerfoxclub.controllers;

import com.example.programmerfoxclub.models.*;
import com.example.programmerfoxclub.service.FoxService;
import com.example.programmerfoxclub.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {

    private final FoxService foxService;
    private final UserService userService;
    private User loggedInUser;

    public MainController(FoxService foxService, UserService userService) {
        this.foxService = foxService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("user", loggedInUser);
        return "index";
    }

    @GetMapping("/register")
    public String registerForm(Model model, @RequestParam(required = false) String text) {
        model.addAttribute("text", text);
        return "register";
    }

    @PostMapping("/register")
    public String register(String username, String password, String foxName, RedirectAttributes redirectAttributes) {
        String text = userService.addNewUser(username, password, foxName);
        if (text == null) {
            loggedInUser = userService.findByUsername(username);
            return "redirect:/";
        } else if (text.equals("user-exists")) {
            redirectAttributes.addAttribute("text", "user-exists");
        } else {
            redirectAttributes.addAttribute("text", "fox-exists");
        }
        return "redirect:/register";
    }

    @GetMapping("/login")
    public String login(Model model, @RequestParam(required = false) String text) {
        model.addAttribute("text", text);
        return "login";
    }

    @PostMapping("/login")
    public String login(String username, String password, RedirectAttributes redirectAttributes) {
        String check = userService.checkUser(username, password);
        if (check != null) {
            redirectAttributes.addAttribute("text", check);
            return "redirect:/login";
        }
        loggedInUser = userService.findByUsername(username);
        return "redirect:/";
    }

    @GetMapping("/trick-center")
    public String selectNewTrick(Model model) {
        model.addAttribute("tricks", Trick.values());
        return "tricks";
    }

    @PostMapping("/trick-center")
    public String addNewTrick(@ModelAttribute("tricks") Trick trick) {
        Fox fox = loggedInUser.getFox();
        foxService.learnNewTrick(fox, trick);
        userService.save(loggedInUser);
        return "redirect:/";
    }

    @GetMapping("/nutritioncenter")
    public String chooseNutrition(Model model) {
        if (loggedInUser != null) {
            Fox fox = loggedInUser.getFox();
            model.addAttribute("newFood", Food.values());
            model.addAttribute("newDrink", Drink.values());
            model.addAttribute("selectedFood", fox.getFood());
            model.addAttribute("selectedDrink", fox.getDrink());
            return "nutrition";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/nutritioncenter")
    public String changeNutrition(@ModelAttribute Food newFood, @ModelAttribute Drink newDrink) {
        Fox fox = loggedInUser.getFox();
        foxService.setNutrition(fox, newFood, newDrink);
        userService.save(loggedInUser);
        return "redirect:/";
    }


}
