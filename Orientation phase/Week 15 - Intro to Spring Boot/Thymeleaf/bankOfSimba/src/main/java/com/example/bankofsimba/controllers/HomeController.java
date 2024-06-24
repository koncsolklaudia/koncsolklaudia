package com.example.bankofsimba.controllers;

import com.example.bankofsimba.models.BankAccount;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("/")
public class HomeController {
    public static List<BankAccount> banks = new ArrayList<>(Arrays.asList(
            new BankAccount("Simba","Lion", 6666, true,true),
            new BankAccount("Timon", "merkaat", 3000, true, true),
            new BankAccount("Pumba", "warthog", 31, false, false)
    ));
    BankAccount simba = new BankAccount("Simba", "lion", 2000, true, true);

    @GetMapping("/show")
    public String bankAccount(Model model) {
        model.addAttribute("name",simba.getName());
        model.addAttribute("balance",simba.getBalance());
        model.addAttribute("type",simba.getAnimalType());
        return "bankaccount";
    }

    @GetMapping("/utext")
    public String showText(Model model){
        String text = "This is an <em>HTML</em> text. <b>Enjoy yourself!</b>";
        model.addAttribute("name", text);
        return "htmlception";
    }

    @GetMapping("/list")
    public String listOfBankAccounts(Model model){
        model.addAttribute("banks", banks);
        return "listofbankaccounts";
    }

    @PostMapping("/list")
    public String raiseBalance(@RequestParam String name){
        for (BankAccount bank : banks){
            if(bank.getName().equals(name) && bank.getKing()){
                bank.setBalance(bank.getBalance() + 100);
            } else if (bank.getName().equals(name) && !bank.getKing()) {
                bank.setBalance((bank.getBalance() + 10));
            }
        }
        return "redirect:/list";
    }

    @GetMapping(path = "/add")
    public String addAccount(@ModelAttribute BankAccount bankAccount, Model model){
        model.addAttribute("bankAccount", bankAccount);
        return "addAccount";
    }

    @PostMapping(path="/add")
    public String saveAccount(BankAccount bankAccount){
        banks.add(bankAccount);
        return "redirect:/list";
    }
}
