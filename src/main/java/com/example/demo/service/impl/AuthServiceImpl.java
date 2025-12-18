package com.example.demo.service.impl;

import com.example.demo.entity.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl {

    @Autowired
    private UserAccountRepository userRepo;

    // Register a new user
    public UserAccount registerUser(UserAccount user) {
        // Default role as USER
        user.setRole("USER");
        return userRepo.save(user);
    }

    // Authenticate user by username and password
    public UserAccount authenticate(String username, String password) {
        UserAccount user = userRepo.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
