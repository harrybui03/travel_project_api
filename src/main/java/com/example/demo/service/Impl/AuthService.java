package com.example.demo.service.impl;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Member;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthService {
    private static final String SECRET_KEY = "b3f9bb2cd8d2f4296d9471a1fef6e392b96f8f88c6a3a28ddfe728cd2910e991";  // Đổi thành key thực tế
    private static final long EXPIRATION_TIME = 86400000; // 1 ngày

    @Autowired
    private EmployeeServiceImpl employeeService;

    public String login(String email, String password) {
        Member member = employeeService.authenticate(email, password);

        // Tạo JWT token
        return Jwts.builder()
                .setSubject(member.getEmail())
                .claim("fullName", member.getFullname())
                .claim("role", member instanceof Employee ? ((Employee) member).getRole() : "N/A")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}