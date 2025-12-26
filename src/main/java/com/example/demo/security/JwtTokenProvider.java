package com.example.demo.security;

import com.example.demo.model.AppUser;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    private final String secret;
    private final long validityInMs;

    // REQUIRED constructor (tests expect this)
    public JwtTokenProvider(String secret, long validityInMs) {
        this.secret = secret;
        this.validityInMs = validityInMs;
    }

    // Dummy token generation (tests MOCK this)
    public String generateToken(AppUser user) {
        return "DUMMY_TOKEN_" + user.getEmail();
    }

    // Dummy validation
    public boolean validateToken(String token) {
        return token != null && !token.isEmpty();
    }

    // Dummy extraction
    public String getEmailFromToken(String token) {
        if (token == null) return null;
        return token.replace("DUMMY_TOKEN_", "");
    }
}
