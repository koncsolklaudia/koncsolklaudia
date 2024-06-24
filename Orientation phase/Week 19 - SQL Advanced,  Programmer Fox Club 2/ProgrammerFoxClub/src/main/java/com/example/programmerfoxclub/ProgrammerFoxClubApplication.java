package com.example.programmerfoxclub;

import com.example.programmerfoxclub.models.Fox;
import com.example.programmerfoxclub.models.User;
import com.example.programmerfoxclub.repositories.FoxRepository;
import com.example.programmerfoxclub.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProgrammerFoxClubApplication implements CommandLineRunner {
    private final FoxRepository foxRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProgrammerFoxClubApplication(FoxRepository foxRepository, UserRepository userRepository) {
        this.foxRepository = foxRepository;
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProgrammerFoxClubApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Fox karak = new Fox("Karak");
        Fox Vuk = new Fox("Vuk");
        foxRepository.save(karak);
        foxRepository.save(Vuk);
        User klaudia = new User("Klau", "1234");
        userRepository.save(klaudia);
    }
}
