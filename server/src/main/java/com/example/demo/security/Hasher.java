package com.example.demo.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Hasher {
    private final PasswordEncoder passwordEncoder;

    public String getHashingValue(String password) {
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }
}
