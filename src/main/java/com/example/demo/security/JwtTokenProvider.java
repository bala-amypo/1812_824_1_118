package com.example.demo.security;

import com.example.demo.model.AppUser;

public class JwtTokenProvider {

    public String generateToken(AppUser user) {
        return "FAKE_TOKEN";
    }

    public boolean validateToken(String token) {
        return token != null && !token.isBlank();
    }

    public String getUsernameFromToken(String token) {
        return "testuser";
    }
}
