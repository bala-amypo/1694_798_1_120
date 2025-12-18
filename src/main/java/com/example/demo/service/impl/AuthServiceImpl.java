package com.example.demo.service.impl;

import com.example.demo.entity.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl {

    @Autowired
    private UserAccountRepository userRepo;

    public UserAccount registerUser(UserAccount user) {
        // Example: default role as USER
        user.setRole("USER"); // fixes setRole() error
        return userRepo.save(user);
    }

    public UserAccount authenticate(String username, String password) {
        UserAccount user = userRepo.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
