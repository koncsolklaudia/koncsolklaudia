package com.example.programmerfoxclub.service;

import com.example.programmerfoxclub.models.User;
import org.springframework.stereotype.Service;

public interface UserService {
    String addNewUser(String username, String password, String foxName);
    void save(User loggedInUser);
    User findByUsername(String username);
    String checkUser(String username, String password);
}
