package com.example.demo.service.impl;

import com.example.demo.request.LoginRequest;
import com.example.demo.request.SignupRequest;
import com.example.demo.response.TokenResponse;
import com.example.demo.entity.Customer;
import com.example.demo.exception.AuthenticationFailedException;
import com.example.demo.exception.EmailAlreadyExistsException;
import com.example.demo.exception.RegistrationFailedException;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService {

    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final String JWT_SECRET = "huongdz";
    private final long JWT_EXPIRATION_MS = 86400000;

    @Autowired
    public AuthServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public TokenResponse signUp(SignupRequest signupRequest) {
        if (customerRepository.existsByEmail(signupRequest.getEmail())) {
            throw new EmailAlreadyExistsException("Email address '" + signupRequest.getEmail() + "' is already registered.");
        }

        Customer customer = new Customer();
        customer.setFullname(signupRequest.getFullName());
        customer.setEmail(signupRequest.getEmail());
        customer.setPassword(passwordEncoder.encode(signupRequest.getPassword()));

        try {
            Customer savedCustomer = customerRepository.save(customer);
            if (savedCustomer != null) {
                return new TokenResponse(generateToken(savedCustomer.getId().toString()));
            } else {
                throw new RegistrationFailedException("Failed to save new user.");
            }
        } catch (Exception e) {
            throw new RegistrationFailedException("Error occurred during user registration.", e);
        }
    }
    @Override
    public TokenResponse signIn(LoginRequest loginRequest) {
        try {
            Customer customer = customerRepository.findByEmail(loginRequest.getEmail());

            if (customer == null) {
                throw new AuthenticationFailedException("Invalid username or password");
            }

            if (!passwordEncoder.matches(loginRequest.getPassword(), customer.getPassword())) {
                throw new AuthenticationFailedException("Invalid username or password");
            }

            return new TokenResponse(generateToken(customer.getId().toString()));
        } catch (AuthenticationFailedException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred during sign-in.", e);
        }
    }
    private String generateToken(String subject) {
        Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
        return JWT.create()
                .withSubject(subject)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + JWT_EXPIRATION_MS))
                .sign(algorithm);
    }
}