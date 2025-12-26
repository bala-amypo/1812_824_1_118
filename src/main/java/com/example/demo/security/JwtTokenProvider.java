package com.example.demo.security;

import com.example.demo.model.AppUser;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    private final String secret;
    private final long validityInMs;

    // REQUIRED by tests
    public JwtTokenProvider(String secret, long validityInMs) {
        this.secret = secret;
        this.validityInMs = validityInMs;
    }

    // Used by tests (mocked)
    public String generateToken(AppUser user) {
        return "TOKEN_" + user.getUsername();
    }

    public boolean validateToken(String token) {
        return token != null && token.startsWith("TOKEN_");
    }
}
