package com.banking_system.service_authentification.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.banking_system.service_authentification.dto.Person;
import com.banking_system.service_authentification.utils.Utils;

@Service
public class AuthService {
    
    private final RestTemplate restTemplate;
    private final String allUsers = "http://localhost:8079/SERVICE-USERS/api/get-persons";

    @Autowired
    Utils utils;

    @Autowired
    PasswordEncoder passwordEncoder;

    public AuthService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String login(String phone, String password) {
        Person[] users = getUsers();
        if (users != null) {
            for(Person user : users) {
                if(user.getTel().equals(phone) && passwordEncoder.matches(password, user.getPassword())) {
                    String token = utils.generateToken(user);
                    return token;
                }
            }
        }

        throw new RuntimeException("Utilisateur inconnu !");
    }

    private Person[] getUsers() {
        ResponseEntity<Person[]> response = restTemplate.getForEntity(allUsers, Person[].class);
        return response.getBody();
    }

}
