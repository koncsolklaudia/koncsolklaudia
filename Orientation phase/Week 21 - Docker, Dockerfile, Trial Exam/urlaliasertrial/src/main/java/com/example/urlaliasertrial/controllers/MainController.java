package com.example.urlaliasertrial.controllers;

import com.example.urlaliasertrial.services.AliasService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final AliasService aliasService;

    @GetMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("aliases", aliasService.getAll());
        return "index";
    }

    //If the alias is already in use redirect to the main page with the error scenario
    //Else
    //Generate a secret code which is just a random 4-digit string consisting of numbers
    //Store the entries in the database
    //If the link is already in the database add the new alias to the existing entry
    //Redirect to the main page with the success scenario

    @PostMapping("/save-link")
    public  String saveLink(@RequestParam String url, @RequestParam String alias, RedirectAttributes redirectAttributes){
    var aliasOpt = aliasService.createAlias(url, alias);
    redirectAttributes.addFlashAttribute("alias", alias);

    if(aliasOpt.isEmpty()){
        redirectAttributes.addFlashAttribute("success", false);
        redirectAttributes.addFlashAttribute("url", url);
    } else{
        redirectAttributes.addFlashAttribute("secret", aliasOpt.get().getSecretCode());
        redirectAttributes.addFlashAttribute("success",true);
    }
    return "redirect:/";
    }

    
}
