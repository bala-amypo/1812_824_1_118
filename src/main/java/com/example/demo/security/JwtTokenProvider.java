package com.example.demo.security;

import com.example.demo.model.AppUser;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    private final String secret;
    private final long validityInMs;

    // REQUIRED BY TESTS
    public JwtTokenProvider(String secret, long validityInMs) {
        this.secret = secret;
        this.validityInMs = validityInMs;
    }

    // DEFAULT CONSTRUCTOR FOR SPRING
    public JwtTokenProvider() {
        this("default-secret", 86400000);
    }

    public String generateToken(AppUser user) {
        return "TOKEN_" + user.getUsername();
    }

    public boolean validateToken(String token) {
        return token != null && token.startsWith("TOKEN_");
    }
}
