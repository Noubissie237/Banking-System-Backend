package com.banking_system.service_authentification.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.banking_system.service_authentification.dto.Person;
import com.banking_system.service_authentification.utils.Utils;

@Service
public class AuthService {
    
    private final RestTemplate restTemplate;
    private final String allUsers = "http://localhost:8079/SERVICE-USERS/api/get-persons";
    private String token;

    @Autowired
    Utils utils;

    @Autowired
    PasswordEncoder passwordEncoder;

    public AuthService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        addAuthorizationHeaderInterceptor(restTemplate);
    }

    public String login(String phone, String password) {
        Person[] users = getUsers();
        if (users != null) {
            for(Person user : users) {
                if(user.getTel().equals(phone) && passwordEncoder.matches(password, user.getPassword())) {
                    token = utils.generateToken(user);
                    return token;
                }
            }
        }

        throw new RuntimeException("Utilisateur inconnu !");
    }

    public boolean checkPassword(String phone, String password) {
        Person[] users  = getUsers();
        if (users != null) {
            for (Person user : users) {
                if(user.getTel().equals(phone) && passwordEncoder.matches(password, user.getPassword())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    private Person[] getUsers() {
        ResponseEntity<Person[]> response = restTemplate.getForEntity(allUsers, Person[].class);
        return response.getBody();
    }

        private void addAuthorizationHeaderInterceptor(RestTemplate restTemplate) {
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>(restTemplate.getInterceptors());
        interceptors.add((request, body, execution) -> {
            if (token != null) {
                request.getHeaders().add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
            }
            return execution.execute(request, body);
        });
        restTemplate.setInterceptors(interceptors);
    }

}
