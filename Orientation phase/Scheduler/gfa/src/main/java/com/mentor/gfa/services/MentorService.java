package com.mentor.gfa.services;


import com.mentor.gfa.models.Mentor;
import com.mentor.gfa.models.TheClass;
import com.mentor.gfa.repositories.ClassRepository;
import com.mentor.gfa.repositories.MentorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MentorService {

    private final ClassRepository classRepository;
    private final MentorRepository mentorRepository;

    public Mentor createMentor(String name, String className) {
        // Check if the class exists in the database
        TheClass theClass = classRepository.findByClassName(className);

        if (theClass == null) {
            throw new ClassNotFoundException("Class not found");
        }

        // Check if a mentor with the same name already exists
        Mentor existingMentor = mentorRepository.findByName(name);

        if (existingMentor != null) {
            throw new MentorAlreadyExistsException("Mentor already exists");
        }

        // Create a new mentor
        Mentor mentor = new Mentor();
        mentor.setName(name);
        mentor.setClassroom(classroom);

        // Save the mentor to the database
        return mentorRepository.save(mentor);
    }

       public Mentor getMentorById(Long mentorId) {
        return mentorRepository.findById(mentorId).orElse(null);
    }
}
