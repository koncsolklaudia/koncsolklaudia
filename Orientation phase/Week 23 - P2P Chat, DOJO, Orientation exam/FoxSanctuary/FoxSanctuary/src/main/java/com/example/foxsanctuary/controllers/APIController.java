package com.example.foxsanctuary.controllers;

import com.example.foxsanctuary.dtos.FoxDto;
import com.example.foxsanctuary.services.CareTakerService;
import com.example.foxsanctuary.services.FoxService;
import lombok.RequiredArgsConstructor;
import com.example.foxsanctuary.dtos.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class APIController {

    public final CareTakerService careTakerService;
    public final FoxService foxService;

//PUT /api/fox/{id}
//Sometimes our application has incorrect data and we need to update it.
//This endpoint accepts payload in JSON format, and updates the entity associated with the provided ID.
//Request JSON payload example:
//{
//  "name": "Miles",
//  "age": 6,
//  "species": "Vulpes vulpes",
//  "careTakerId": 45
//}

    @PutMapping("/fox/{id}")
    public ResponseEntity<Message> updateFox(@PathVariable Long id, @RequestBody FoxDto foxDto) {
        ResponseEntity<Message> responseEntity = validateFoxDto(foxDto);
        if (responseEntity != null) {
            return responseEntity;
        }
        if (foxService.getFoxById(id) == null) {
            return ResponseEntity.badRequest().body(new Message("Invalid id provided."));
        }
        if (foxDto.getCareTakerId() != null && careTakerService.getCaretakerById(foxDto.getCareTakerId()) == null) {
            return ResponseEntity.badRequest().body(new Message("Invalid care taker id provided."));
        }
        foxService.updateFox(id, foxDto.getName(), Integer.parseInt(foxDto.getAge()), foxDto.getSpecies(), foxDto.getCareTakerId());
        return ResponseEntity.ok(new Message("Fox successfully added."));
    }

    private ResponseEntity<Message> validateFoxDto(FoxDto foxDto) {
        if (foxDto.getName() == null) {
            return ResponseEntity.badRequest().body(new Message("Missing name."));
        }
        if (foxDto.getAge() == null) {
            return ResponseEntity.badRequest().body(new Message("Missing age."));
        }
        if (foxDto.getSpecies() == null) {
            return ResponseEntity.badRequest().body(new Message("Missing species."));

        }
        return null;
    }
}

