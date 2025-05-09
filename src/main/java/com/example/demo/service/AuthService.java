package com.example.demo.service;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.SignupRequest;
import com.example.demo.dto.TokenResponse;

public interface AuthService {
    TokenResponse signUp(SignupRequest signupRequest);

    TokenResponse signIn(LoginRequest loginRequest);
}