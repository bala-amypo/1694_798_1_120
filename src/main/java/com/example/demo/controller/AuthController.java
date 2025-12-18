package com.example.demo.controller;

import com.example.demo.entity.UserAccount;
import com.example.demo.service.impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthServiceImpl authService;

    @PostMapping("/register")
    public UserAccount register(@RequestBody UserAccount user) {
        return authService.registerUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserAccount user) {
        UserAccount u = authService.authenticate(user.getUsername(), user.getPassword());
        if (u != null) {
            return "Login successful for user: " + u.getUsername();
        } else {
            return "Invalid credentials";
        }
    }
}
