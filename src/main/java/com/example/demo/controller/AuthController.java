package com.example.demo.controller;

import com.example.demo.entity.UserAccount;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final List<UserAccount> users = new ArrayList<>();

    @PostMapping("/register")
    public UserAccount register(@RequestBody UserAccount user) {
        users.add(user);
        return user;
    }

    @PostMapping("/login")
    public String login(@RequestBody UserAccount user) {
        return users.stream()
                .anyMatch(u -> u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword()))
                ? "Login successful" : "Invalid credentials";
    }
}
