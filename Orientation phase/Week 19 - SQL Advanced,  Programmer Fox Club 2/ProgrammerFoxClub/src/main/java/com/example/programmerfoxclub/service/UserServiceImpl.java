package com.example.programmerfoxclub.service;

import com.example.programmerfoxclub.models.Fox;
import com.example.programmerfoxclub.repositories.FoxRepository;
import com.example.programmerfoxclub.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.programmerfoxclub.models.User;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final FoxRepository foxRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, FoxRepository foxRepository) {
        this.userRepository = userRepository;
        this.foxRepository = foxRepository;
    }
    @Override
    public String addNewUser(String username, String password, String foxName) {
        if (userRepository.findUserByNameContainingIgnoreCase(username) == null) {
            if (foxRepository.findFoxByNameContainingIgnoreCase(foxName) == null) {
                Fox fox = new Fox(foxName);
                User user = new User(username, password);
                user.setFox(fox);
                fox.setUser(user);
                userRepository.save(user);
                return null;
            }
            return "fox-exists";
        }
        return "user-exists";
    }

    @Override
    public void save(User loggedInUser) {
        userRepository.save(loggedInUser);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findUserByNameContainingIgnoreCase(username);
    }


    @Override
    public String checkUser(String username, String password) {
        User user = userRepository.findUserByNameContainingIgnoreCase(username);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                return null;
            }
        }
        return "login-fail";
    }
}

