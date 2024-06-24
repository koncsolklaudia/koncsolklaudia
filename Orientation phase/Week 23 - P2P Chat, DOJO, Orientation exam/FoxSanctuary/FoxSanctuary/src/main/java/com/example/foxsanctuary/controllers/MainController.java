package com.example.foxsanctuary.controllers;

import com.example.foxsanctuary.dtos.NewFoxFormDTO;
import com.example.foxsanctuary.services.FoxService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class MainController {

    //Each fox must have a name, age, and species.
    //Fox can have only 1 caretaker, but it doesn't have to have one (it can be empty).
    //Each caretaker must have a name
    //The caretakers can have more foxes at a time.
    //Database
    //Requirements are:


    //This endpoint displays the following information:
    //A table of all the foxes currently at the sanctuary in alphabetical order by name,
    // including their name, age, species, current care taker (if any)
    //If the fox has no care taker, display "No care taker assigned" instead of the care taker's name.
    //A form for entering the details of a new fox to bring to the sanctuary.
    // It should submit the data to POST /fox/add

    private final FoxService foxService;

    @GetMapping("/")
    public String getMainPage(Model model, @ModelAttribute NewFoxFormDTO newFoxFormDTO) {
        model.addAttribute("foxes", foxService.findAllFoxes());
        return "index";
    }

    //POST /fox/add
    //This endpoint handles the addition of new foxes to the sanctuary from the form.
    //It accepts all the necessary data from the main page's form.
    //Redirect the user back to the main page if the fox is successfully added.
    //If it's unsuccessful due to invalid input,
    // return the user back to the main page and display an error message saying
    // " Error: Invalid input" as seen here:

    @PostMapping("/fox/add")
    public String addNewFox(RedirectAttributes redirectAttributes, NewFoxFormDTO newFoxFormDTO) {
        if (newFoxFormDTO == null) {
            redirectAttributes.addFlashAttribute("error", true);
            return "redirect:/";
        }
        String result = foxService.addNewFox(newFoxFormDTO);
        if (!result.equals("ok")) {
            redirectAttributes.addFlashAttribute("error", true);
            redirectAttributes.addFlashAttribute("newFoxFormDTO", newFoxFormDTO);
            return "redirect:/";
        }
        return "redirect:/";
    }

}
