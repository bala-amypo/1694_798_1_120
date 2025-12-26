package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.entity.UserAccount;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.AuthService;
import org.springframework.stereotype.Service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;
@Service
public class AuthServiceImpl implements AuthService {

    private final UserAccountRepository repo;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserAccountRepository repo,
                           PasswordEncoder encoder,
                           AuthenticationManager authManager,
                           JwtUtil jwtUtil) {
        this.repo = repo;
        this.encoder = encoder;
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public AuthResponseDto register(RegisterRequestDto r) {
        if (repo.existsByEmail(r.getEmail())) {
            throw new BadRequestException("Email already exists");
        }

        UserAccount user = new UserAccount();
        user.setEmail(r.getEmail());
        user.setPassword(encoder.encode(r.getPassword()));
        user.setRole(r.getRole());

        repo.save(user);

        String token = jwtUtil.generateToken(Map.of(), user.getEmail());
        return new AuthResponseDto(token, user.getId(), user.getEmail(), user.getRole());
    }

    @Override
    public AuthResponseDto login(AuthRequestDto r) {
        UserAccount user = repo.findByEmail(r.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        String token = jwtUtil.generateToken(Map.of(), user.getEmail());
        return new AuthResponseDto(token, user.getId(), user.getEmail(), user.getRole());
    }
}
