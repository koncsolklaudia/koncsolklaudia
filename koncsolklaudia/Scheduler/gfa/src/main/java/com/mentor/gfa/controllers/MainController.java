package com.mentor.gfa.controllers;

import com.mentor.gfa.models.Mentor;
import com.mentor.gfa.models.TheClass;
import com.mentor.gfa.repositories.ClassRepository;
import com.mentor.gfa.repositories.MentorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final MentorRepository mentorRepository;
    private final ClassRepository classRepository;

    @GetMapping("/")
    public String index(Model model) {
        List<Mentor> mentors = mentorRepository.findAll();
        List<TheClass> classes = classRepository.findAll();

        model.addAttribute("mentors", mentors);
        model.addAttribute("classes", classes);

        return "index";
    }

}
