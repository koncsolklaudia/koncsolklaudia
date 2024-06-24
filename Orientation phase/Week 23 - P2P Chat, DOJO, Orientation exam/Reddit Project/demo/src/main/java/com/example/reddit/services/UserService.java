package com.example.reddit.services;

import com.example.reddit.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    Iterable<User> findAll();
    String registerUser(String name, String password, String retype);
    boolean retypeCheck(String password, String retype);
}
