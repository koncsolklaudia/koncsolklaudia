package com.example.reddit.controllers;

import com.example.reddit.models.Post;
import com.example.reddit.models.User;
import com.example.reddit.services.PostService;
import com.example.reddit.services.ScoringService;
import com.example.reddit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {

    private final UserService userService;
    private final ScoringService scoringService;
    private final PostService postService;
    private User loggedInUser;
    @Autowired
    public MainController(UserService userService, ScoringService scoringService, PostService postService) {
        this.userService = userService;
        this.scoringService = scoringService;
        this.postService = postService;
    }

    @GetMapping("/")
    public String index(Model model, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "0") int offset){
        model.addAttribute("user", loggedInUser);
        model.addAttribute("offset", offset);
        model.addAttribute("posts", postService.findAll(offset, size));
        model.addAttribute("users", userService.findAll());
        return "index";
    }

    @PostMapping("/{postId}")
    public String voting(@PathVariable Long postId, Long scoringValue) {
        if (loggedInUser != null) {
            Post post = postService.findById(postId);
            scoringService.addNewScoring(loggedInUser, post, scoringValue);
            postService.changeScore(post, scoringService.getSumOfScoring(postId));
            return "redirect:/";
        }
        return "redirect:/login";
    }

    @GetMapping("/submit")
    public String newPostView(Model model){
        model.addAttribute("user", loggedInUser);
        return "submit";
    }

    @PostMapping("/submit")
    public String addNewPost(@ModelAttribute Post post){
        if (loggedInUser == null) {
            return "redirect:/submit";
        } else {
            postService.createNewPost(post, loggedInUser.getId());
            return "redirect:/";
        }
    }

    @GetMapping("/register")
    public String register(Model model, @RequestParam (required = false) String outcome) {
        model.addAttribute("outcome", outcome);
        return "register";
    }

    @PostMapping("/register")
    public String registering(String name, String password, String retype, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("outcome", userService.registerUser(name, password, retype));
        return "redirect:/register";
    }




}
