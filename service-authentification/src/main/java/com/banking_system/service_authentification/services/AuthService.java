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
import com.banking_system.service_authentification.exceptions.ApplicationException;
import com.banking_system.service_authentification.exceptions.InvalidCredentialsException;
import com.banking_system.service_authentification.exceptions.UserNotFoundException;
import com.banking_system.service_authentification.utils.Utils;

@Service
public class AuthService {

    private final RestTemplate restTemplate;
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
        String allUsers = "https://proxy.quick-send.site/SERVICE-USERS/api/get-persons";
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
