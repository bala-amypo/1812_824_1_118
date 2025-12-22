package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.AppUser;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AppUserRepository repo;

    @Override
    public AppUser registerUser(AppUser user) {
        return repo.save(user);
    }

    @Override
    public AppUser findByUsername(String username) {
        return repo.findByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repo.existsByEmail(email);
    }
}
