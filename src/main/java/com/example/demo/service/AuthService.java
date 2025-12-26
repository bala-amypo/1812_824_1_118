package com.example.demo.service;

import com.example.demo.model.AppUser;

import java.util.Optional;

public interface AuthService {

    AppUser registerUser(AppUser user);

    Optional<AppUser> findByUsername(String email);

    boolean existsByEmail(String email);
}
