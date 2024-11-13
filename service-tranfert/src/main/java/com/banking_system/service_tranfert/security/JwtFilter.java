package com.banking_system.service_tranfert.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.banking_system.service_tranfert.utils.JwtUtils;


import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            try {
                Claims claims = jwtUtils.extractAllClaims(token);

                String role = claims.get("role", String.class);
                if ("CLIENT".equals(role)) {
                    filterChain.doFilter(request, response);
                    return;
                } else {
                    response.sendError(HttpStatus.FORBIDDEN.value(), "Accès refusé : rôle invalide");
                    return;
                }

            } catch (ExpiredJwtException e) {
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "Token expiré : veuillez vous reconnecter.");
                return;

            } catch (Exception e) {
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "Token invalide.");
                return;
            }
        }

        response.sendError(HttpStatus.UNAUTHORIZED.value(), "Authorization header absent ou invalide.");
    }
}
