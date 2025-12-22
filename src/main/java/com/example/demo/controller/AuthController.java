package com.example.demo.controller;

import com.example.demo.model.AppUser;
import com.example.demo.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public AppUser register(@RequestBody AppUser user) {
        return authService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody AppUser user) {
        return authService.login(user.getEmail(), user.getPassword());
    }
}
