package com.banking_system.service_authentification.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.banking_system.service_authentification.dto.Agence;
import com.banking_system.service_authentification.dto.Person;
import com.banking_system.service_authentification.exceptions.ApplicationException;
import com.banking_system.service_authentification.exceptions.InvalidCredentialsException;
import com.banking_system.service_authentification.exceptions.UserNotFoundException;
import com.banking_system.service_authentification.utils.JwtTokenUtil;
import com.banking_system.service_authentification.utils.Utils;


@Service
public class AuthService {

    private final RestTemplate restTemplate;
    private String token;

    @Autowired
    Utils utils;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    PasswordEncoder passwordEncoder;

    public AuthService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        addAuthorizationHeaderInterceptor(restTemplate);
    }

    public String login(String phone, String password) {
        System.out.println("NUMERO : " + phone);
        System.out.println("PASS : " + password);
        try {
            Person[] users = getUsers();

            if (users == null || users.length == 0) {
                throw new UserNotFoundException("Aucun utilisateur n'a été trouvé.");
            }

            for (Person user : users) {
                if (user.getTel().equals(phone)) {
                    if (passwordEncoder.matches(password, user.getPassword())) {
                        return utils.generateToken(user);
                    }
                }
            }

            throw new InvalidCredentialsException("Numéro de téléphone ou mot de passe incorrect.");
        } catch (UserNotFoundException | InvalidCredentialsException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ApplicationException("Une erreur inattendue s'est produite lors de la tentative de connexion.",
                    ex);
        }
    }

// Dans votre méthode de contrôle
public ResponseEntity<?> loginAdmin(String login, String password) {
    try {
        Agence[] agences = getAgence();
        if (agences == null || agences.length == 0) {
            return ResponseEntity.status(404).body("Aucune agence trouvée");
        }

        for (Agence agence : agences) {
            if (agence.getLogin().equals(login) && agence.getPassword().equals(password)) {
                String token = jwtTokenUtil.generateToken(agence);
                return ResponseEntity.ok().body(Map.of("token", token));
            }
        }
        return ResponseEntity.status(401).body("Login ou mot de passe incorrect");
    } catch (Exception e) {
        return ResponseEntity.status(500).body("Une erreur est survenue : " + e.getMessage());
    }
}


    public boolean checkPassword(String phone, String password) {
        Person[] users = getUsers();
        if (users != null) {
            for (Person user : users) {
                if (user.getTel().equals(phone) && passwordEncoder.matches(password, user.getPassword())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    private Person[] getUsers() {
        String allUsers = "http://service-proxy:8079/SERVICE-USERS/api/get-persons";
        ResponseEntity<Person[]> response = restTemplate.getForEntity(allUsers, Person[].class);
        return response.getBody();
    }

    private Agence[] getAgence() {
        String allAgences = "http://service-proxy:8079/SERVICE-ADMIN/api/agence/get-agences";
        ResponseEntity<Agence[]> response = restTemplate.getForEntity(allAgences, Agence[].class);
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
