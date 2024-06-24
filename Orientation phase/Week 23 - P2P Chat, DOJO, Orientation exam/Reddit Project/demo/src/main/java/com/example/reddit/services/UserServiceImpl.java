package com.example.reddit.services;

import com.example.reddit.models.User;
import com.example.reddit.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public boolean checkUser(String name) {
        return userRepository.findPassword(name) != null;
    }

    @Override
    public boolean retypeCheck(String password, String retype) {
        return password.equals(retype);
    }
    @Override
    public String registerUser(String name, String password, String retype) {
        if (!checkUser(name)) {
            if (retypeCheck(password, retype)) {
                User user = new User(name, password);
                userRepository.save(user);
                return "Welcome!";
            } else {
                return "The two passwords don't match!";
            }
        } else {
            return "The username is already taken!";
        }
    }
}
