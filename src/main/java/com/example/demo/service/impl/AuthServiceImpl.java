package com.example.demo.service.impl;

import com.example.demo.dto.AuthRequestDto;
import com.example.demo.dto.AuthResponseDto;
import com.example.demo.entity.UserAccount;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserAccountRepository repo;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public AuthResponseDto register(AuthRequestDto dto) {

        UserAccount user = new UserAccount();
        user.setEmail(dto.getEmail());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setRole("USER");

        UserAccount saved = repo.save(user);

        String token = jwtUtil.generateToken(saved.getEmail());

        return new AuthResponseDto(saved.getId(),
                saved.getEmail(),
                saved.getRole(),
                token);
    }

    @Override
    public AuthResponseDto login(AuthRequestDto dto) {

        UserAccount user = repo.findByEmail(dto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!encoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new AuthResponseDto(user.getId(),
                user.getEmail(),
                user.getRole(),
                token);
    }
}
