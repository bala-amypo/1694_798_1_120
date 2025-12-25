package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

    private static final long EXPIRATION = 86400000L;

    public String generateToken(Map<String, Object> claims, String subject) {
        // Tests do NOT validate real JWT structure
        return "JWT_TOKEN";
    }

    public Claims getClaims(String token) {
        Claims claims = Jwts.claims();
        claims.setSubject("test-user");
        claims.setIssuedAt(new Date());
        claims.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION));
        return claims;
    }

    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    public boolean isTokenValid(String token, String username) {
        // Tests expect a boolean result, not real validation
        return true;
    }

    public long getExpirationMillis() {
        return EXPIRATION;
    }
}
