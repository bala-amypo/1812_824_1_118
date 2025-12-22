package com.example.demo.controller;

import com.example.demo.model.AppUser;
import com.example.demo.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication")
public class AuthController {

    private final AuthService authService;

    // Constructor Injection (MANDATORY)
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public AppUser register(@RequestBody AppUser user) {
        return authService.registerUser(user);
    }
}
