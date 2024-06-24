package com.example.usefulutilities.controllers;

import com.example.usefulutilities.services.GenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/useful")
public class HomeController {

    public final GenService genService;

    @Autowired
    public HomeController(GenService genService) {
        this.genService = genService;
    }

    @GetMapping("/useful")
    public String indexPage(Model model){
        return "index";
    }

    @GetMapping("/colored")
    public String coloredPage(Model model) {
        model.addAttribute("color", genService.randomColor());
        return "background";
    }

    @GetMapping("/email")
    public String emailValidator(Model model, @RequestParam(required = false) String email) {
        model.addAttribute("answer", genService.validateEmail(email));
        model.addAttribute("emailToCheck", email);
        return "email";
    }

    @PostMapping("/email")
    public String validateEmail(Model model, @RequestParam(required = false) String email) {
        model.addAttribute("answer", genService.validateEmail(email));
        return "email";
    }

    @GetMapping("/coding")
    public String renderCodingPage(@RequestParam(required = false) String text, Integer number, Model model){
        text = "HEY";
        number =354345;
        model.addAttribute("text",genService.caesar(text, number));
        return "caesar";
    }

}