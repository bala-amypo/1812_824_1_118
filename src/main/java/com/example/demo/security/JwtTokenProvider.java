package com.example.demo.security;

import com.example.demo.model.AppUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret:default-secret}")
    private String jwtSecret;

    public String generateToken(AppUser user) {
        // simplified token for tests
        return "TOKEN_" + user.getUsername();
    }

    public boolean validateToken(String token) {
        return token != null && token.startsWith("TOKEN_");
    }
}
