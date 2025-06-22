package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtils {

    private final String SECRET = "E+BBdM5o//evMugIm7Sp7iQWS951FSlPL+HxFdee/nZJ7tvK+hLHb8KDnfHRXNAuJSGkZful+DukSs36g3uRlg=="; // tốt nhất lấy từ config/env
    private final long EXPIRATION = 86400000; // 1 ngày

    // Tạo SecretKey từ chuỗi SECRET
    private final SecretKey secretKey = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    public String generateToken(CustomUserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername()) // email
                .claim("role", userDetails.getEmployee().getRole().name())
                .claim("id", userDetails.getEmployee().getId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(secretKey, SignatureAlgorithm.HS512) // đổi cách gọi signWith
                .compact();
    }

    public String extractEmail(String token) {
        return parseClaims(token).getSubject();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String email = extractEmail(token);
        return (email.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        final Date expiration = parseClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    // Hàm helper parse token claims với secretKey đúng chuẩn
    private Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey) // dùng parserBuilder và secretKey
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}