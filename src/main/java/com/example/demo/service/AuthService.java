package com.example.demo.service;
import com.example.demo.request.LoginRequest;
import com.example.demo.request.SignupRequest;
import com.example.demo.response.TokenResponse;

public interface AuthService {
    TokenResponse signUp(SignupRequest signupRequest);

    TokenResponse signIn(LoginRequest loginRequest);
}