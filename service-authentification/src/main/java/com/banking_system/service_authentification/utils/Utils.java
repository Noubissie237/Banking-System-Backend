package com.banking_system.service_authentification.utils;

import java.util.Date;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.banking_system.service_authentification.dto.Person;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.JwtBuilder;

@Component
public class Utils {

    @Value("${jwt.secret}")
    private String secretKey;

    public String generateToken(Person user) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 300000);

        JwtBuilder builder = Jwts.builder()
            .setSubject(user.getTel())
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(key)
            .claim("phone", user.getTel())
            .claim("email", user.getEmail())
            .claim("role", user.getRole());

        return builder.compact();
    }
}
