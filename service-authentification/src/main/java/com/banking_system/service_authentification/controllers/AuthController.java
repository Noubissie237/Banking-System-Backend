package com.banking_system.service_authentification.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking_system.service_authentification.dto.AdminLoginRequest;
import com.banking_system.service_authentification.dto.LoginRequest;
import com.banking_system.service_authentification.services.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        System.out.println("-----------Demande de login : "+ loginRequest.getPhone()+" "+loginRequest.getPassword());
        return authService.login(loginRequest.getPhone(), loginRequest.getPassword());
    }

    @PostMapping("/check-password")
    public boolean checkPassword(@RequestBody LoginRequest checking) {
        return authService.checkPassword(checking.getPhone(), checking.getPassword());
    }
    
    @PostMapping("/login-admin")
    public ResponseEntity<?>  loginAdmin(@RequestBody AdminLoginRequest admin) {
        System.out.println("recu");
        return authService.loginAdmin(admin.getLogin(), admin.getPassword());
    }
    
}
