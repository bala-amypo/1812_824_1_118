package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.AppUser;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AppUserRepository repo;

    public AuthServiceImpl(AppUserRepository repo) {
        this.repo = repo;
    }

    @Override
    public AppUser registerUser(AppUser user) {
        if (repo.existsByUsername(user.getUsername())) {
            throw new BadRequestException("Username already taken");
        }
        if (repo.existsByEmail(user.getEmail())) {
            throw new BadRequestException("Email already taken");
        }
        return repo.save(user);
    }

    @Override
    public AppUser findByUsername(String username) {
        return repo.findByUsername(username)
                .orElseThrow(() -> new BadRequestException("User not found"));
    }

    @Override
    public boolean existsByEmail(String email) {
        return repo.existsByEmail(email);
    }
}
