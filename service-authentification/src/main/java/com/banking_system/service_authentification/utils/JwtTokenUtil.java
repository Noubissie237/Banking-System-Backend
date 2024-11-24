package com.banking_system.service_authentification.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.JwtBuilder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.banking_system.service_authentification.dto.Agence;

import java.util.Date;

import javax.crypto.SecretKey;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secretKey;


    public String generateToken(Agence agence) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 86400000);
        
        JwtBuilder builder = Jwts.builder()
                .setSubject(agence.getLogin())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key)
                .claim("id", agence.getId())
                .claim("nom", agence.getNom())
                .claim("login", agence.getLogin())
                .claim("capital", agence.getCapital());

        return builder.compact();
    }
}
