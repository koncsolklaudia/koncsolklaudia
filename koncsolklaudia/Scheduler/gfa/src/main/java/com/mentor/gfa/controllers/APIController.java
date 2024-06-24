package com.mentor.gfa.controllers;

import com.mentor.gfa.dtos.ErrorResponse;
import com.mentor.gfa.dtos.MentorRequest;
import com.mentor.gfa.dtos.MentorResponse;
import com.mentor.gfa.models.Mentor;
import com.mentor.gfa.models.TheClass;
import com.mentor.gfa.repositories.MentorRepository;
import com.mentor.gfa.services.MentorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class APIController {

    private final MentorRepository mentorRepository;
    private final MentorService mentorService;


    @PostMapping("/mentor")
    public String createMentor(@ModelAttribute Mentor mentor) {
        mentorRepository.save(mentor);
        return "redirect:/mentor/" + mentor.getId();
    }

    @GetMapping("/mentor/{id}")
    public ResponseEntity<?> mentorDetails(@PathVariable Long id) {
        Mentor mentor = mentorRepository.findById(id).orElse(null);
        if (mentor != null) {
            // Mentor found, return mentor details
            return ResponseEntity.ok(mentor);
        } else {
            // Mentor not found, return a 404 response
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mentor not found");
        }
    }


    @GetMapping("/api/mentors")
    public ResponseEntity<?> getMentorsByClassName(@RequestParam String className) {
        if (className == null || className.isEmpty()) {
            ErrorResponse errorResponse = new ErrorResponse("className is required");
            return ResponseEntity.badRequest().body(errorResponse);
        }

        List<Mentor> mentors = mentorService.getMentorsByClassName(className);

        if (mentors.isEmpty()) {
            // Return a 200 status code with an empty array
            return ResponseEntity.ok(mentors);
        }

        return ResponseEntity.ok(mentors);
    }


//This endpoint should create a new mentor

    @PostMapping("/api/mentors")
    public ResponseEntity<?> createMentor(@RequestBody MentorRequest mentorRequest) throws Exception{
        Mentor mentor = null;
        String errorMessage = null;

        if (mentorRequest.getName() == null || mentorRequest.getName().isEmpty() || mentorRequest.getClassName() == null || mentorRequest.getClassName().isEmpty()) {
            errorMessage = "Name and className are required.";
        } else {
            mentor = mentorService.createMentor(mentorRequest.getName(), mentorRequest.getClassName());
        }

        if (errorMessage != null) {
            ErrorResponse errorResponse = new ErrorResponse(errorMessage);
            return ResponseEntity.badRequest().body(errorResponse);
        } else {
            MentorResponse response = new MentorResponse(mentor.getId(), mentor.getName(), mentorRequest.getClassName());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
    }

//## PUT `/api/mentors/{mentorId}`


    @PutMapping("/api/mentors/{mentorId}")
    public ResponseEntity<?> updateMentor(
            @PathVariable Long mentorId,
            @RequestBody MentorRequest mentorRequest) {
        Mentor mentor = mentorService.getMentorById(mentorId);

        if (mentor == null) {
            ErrorResponse errorResponse = new ErrorResponse("Mentor not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        if (mentorRequest.getName() == null || mentorRequest.getName().isEmpty() || mentorRequest.getClassName() == null || mentorRequest.getClassName().isEmpty()) {
            ErrorResponse errorResponse = new ErrorResponse("Name and className are required.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        TheClass theClass = mentorService.getClassByClassName(mentorRequest.getClassName());

        if (theClass== null) {
            ErrorResponse errorResponse = new ErrorResponse("Class not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        mentor.setName(mentorRequest.getName());
        mentor.setTheClass(theClass);

        mentorService.updateMentor(mentor);

        MentorResponse response = new MentorResponse(mentor.getId(), mentor.getName(), mentorRequest.getClassName());
        return ResponseEntity.ok(response);

    }
    
}


