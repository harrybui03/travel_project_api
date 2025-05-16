package com.example.demo.controller;

import com.example.demo.request.LoginRequest;
import com.example.demo.request.SignupRequest;
import com.example.demo.response.TokenResponse;
import com.example.demo.exception.EmailAlreadyExistsException;
import com.example.demo.exception.RegistrationFailedException;
import com.example.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<TokenResponse> signUp(@RequestBody SignupRequest signupRequest) {
        try {
            TokenResponse tokenResponse = authService.signUp(signupRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(tokenResponse);
        } catch (EmailAlreadyExistsException e) {
            return new ResponseEntity<>(new TokenResponse(e.getMessage()), HttpStatus.CONFLICT);
        } catch (RegistrationFailedException e) {
            return new ResponseEntity<>(new TokenResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<TokenResponse> signIn(@RequestBody LoginRequest loginRequest) {
        TokenResponse tokenResponse = authService.signIn(loginRequest);
        if (tokenResponse != null) {
            return ResponseEntity.ok(tokenResponse);
        } else {
            return new ResponseEntity<>(new TokenResponse("Invalid credentials"), HttpStatus.UNAUTHORIZED);
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<TokenResponse> handleGenericException(Exception ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(new TokenResponse("An unexpected error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}