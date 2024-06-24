package com.example.foxsanctuary.services;

import com.example.foxsanctuary.dtos.NewFoxFormDTO;
import com.example.foxsanctuary.models.Caretaker;
import com.example.foxsanctuary.models.Fox;
import com.example.foxsanctuary.repositories.FoxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.apache.el.lang.ELArithmetic.isNumber;

@Service
@RequiredArgsConstructor
public class FoxService {

    private final FoxRepository foxRepository;
    private final CareTakerService careTakerService;

    public Fox findAllFoxes() {
        return (Fox) foxRepository.findAll().stream().sorted(Comparator.comparing(Fox::getName)).toList();
    }


    //   //POST /fox/add
    //    //This endpoint handles the addition of new foxes to the sanctuary from the form.
    //    //It accepts all the necessary data from the main page's form.
    //    //Redirect the user back to the main page if the fox is successfully added.
    //    //If it's unsuccessful due to invalid input,
    //    // return the user back to the main page and display an error message saying
    //    // " Error: Invalid input" as seen here:

    public String addNewFox(NewFoxFormDTO newFoxFormDTO) {

        String result = checkInputForCreatingAndUpdatingFox(newFoxFormDTO);
        if (!result.equals("ok")) {
            return result;
        }
        Fox newFox = new Fox();
        newFox.setName(newFoxFormDTO.getName());
        newFox.setSpecies(newFoxFormDTO.getSpecies());
        int age = Integer.parseInt(newFoxFormDTO.getAge());
        newFox.setAge(age);
        foxRepository.save(newFox);
        return result;
    }

    private String checkInputForCreatingAndUpdatingFox(NewFoxFormDTO newFoxFormDTO) {
        List<String> missingFields = new ArrayList<>();
        List<String> errorSentences = new ArrayList<>();
        if (newFoxFormDTO.getName() == null) {
            missingFields.add("name");
        }
        if (newFoxFormDTO.getSpecies() == null) {
            missingFields.add("species");
        }
        int age = 0;
        if ((newFoxFormDTO.getAge() == null)) {
            missingFields.add("age");
        } else {
            if (!isNumber(newFoxFormDTO.getAge())) {
                errorSentences.add("Age must be a number.");
            } else {
                age = Integer.parseInt(newFoxFormDTO.getAge());
                if (age < 0) {
                    errorSentences.add("Age must be a greater than 0.");
                }
            }
        }
        if (!missingFields.isEmpty() || !errorSentences.isEmpty()) {
            String errorResult = "";
            if (!missingFields.isEmpty()) {
                errorResult = errorResult.concat("Missing ").concat(String.join(", ", missingFields)).concat(". ");
            }
            if (!errorSentences.isEmpty()) {
                errorResult = errorResult.concat(String.join(" ", errorSentences));
            }
            return errorResult;
        }
        return "ok";

    }

    public Fox getFoxById(Long id) {
        return foxRepository.findById(id).orElse(null);
    }

    public void updateFox(Long id, String name, Integer age, String species, Long careTakerId) {
        if(careTakerId == null){
            foxRepository.save(new Fox(id, name, age, species, null));
            return;
        }
        Caretaker caretaker = careTakerService.getCaretakerById(careTakerId);
        foxRepository.save(new Fox(id, name, age, species, caretaker));

    }
}

