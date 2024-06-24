package com.example.foxsanctuary.services;

import com.example.foxsanctuary.models.Caretaker;
import com.example.foxsanctuary.repositories.CaretakerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CareTakerService {

    CaretakerRepository caretakerRepository;

    public CareTakerService(CaretakerRepository caretakerRepository) {
        this.caretakerRepository = caretakerRepository;
    }

    public Caretaker getCaretakerById(Long id){
        return caretakerRepository.findById(id).orElse(null);
    }
}
