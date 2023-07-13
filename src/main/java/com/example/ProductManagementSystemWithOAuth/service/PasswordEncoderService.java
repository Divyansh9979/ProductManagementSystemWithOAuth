package com.example.ProductManagementSystemWithOAuth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncoderService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private PasswordEncoderService() {
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    public String encodePassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }
}
