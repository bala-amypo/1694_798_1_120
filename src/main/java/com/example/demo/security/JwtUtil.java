package com.example.demo.security;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JwtUtil {

    public String generateToken(Map<String, Object> claims, String subject) {
        return "JWT_TOKEN";
    }

    public Claims getClaims(String token) {
        return null;
    }

    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    public boolean isTokenValid(String token, String username) {
        return true;
    }

    public long getExpirationMillis() {
        return 86400000L;
    }
}
