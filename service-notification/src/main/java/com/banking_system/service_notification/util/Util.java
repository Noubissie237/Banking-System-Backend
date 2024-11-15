package com.banking_system.service_notification.util;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.banking_system.service_notification.events.Account;
import com.banking_system.service_notification.events.Solde;
@Component
public class Util {

    private final RestTemplate restTemplate = new RestTemplate();

    public Account getEmail(String number) throws IOException {
        String url = "http://localhost:8079/SERVICE-USERS/api/client/get/" + number;

        try {
            Account account = restTemplate.getForObject(url, Account.class);
            if (account == null) {
                throw new IllegalArgumentException("Aucun compte trouvé avec le numéro : " + number);
            }
            return account;
        } catch (HttpClientErrorException.NotFound e) {
            throw new IllegalArgumentException("Aucun compte trouvé avec le numéro : " + number);
        } catch (RestClientException e) {
            throw new IOException("Erreur de connexion au service distant", e);
        }
    }

    public Account getEmailAgent(String number) throws IOException {
        String url = "http://localhost:8079/SERVICE-USERS/api/agent/get/" + number;

        try {
            Account account = restTemplate.getForObject(url, Account.class);
            if (account == null) {
                throw new IllegalArgumentException("Aucun compte trouvé avec le numéro : " + number);
            }
            return account;
        } catch (HttpClientErrorException.NotFound e) {
            throw new IllegalArgumentException("Aucun compte trouvé avec le numéro : " + number);
        } catch (RestClientException e) {
            throw new IOException("Erreur de connexion au service distant", e);
        }
    }

    public Solde getSoldeAgent(String number) throws IOException {
            String url = "http://localhost:8079/SERVICE-ACCOUNT-MANAGEMENT/api/account/get/" + number;

        try {
            Solde account = restTemplate.getForObject(url, Solde.class);
            if (account == null) {
                throw new IllegalArgumentException("Aucun compte trouvé avec le numéro : " + number);
            }
            return account;

        } catch (HttpClientErrorException.NotFound e) {
            throw new IllegalArgumentException("Aucun compte trouvé avec le numéro : " + number);
        } catch (RestClientException e) {
            throw new IOException("Erreur de connexion au service distant", e);
        }
    }

    public Solde getsoldeClient(String number) throws IOException {
        String url = "http://localhost:8079/SERVICE-ACCOUNT-MANAGEMENT/api/account/get/" + number;

        try {
            Solde account = restTemplate.getForObject(url, Solde.class);
            if (account == null) {
                throw new IllegalArgumentException("Aucun compte trouvé avec le numéro : " + number);
            }
            return account;

        } catch (HttpClientErrorException.NotFound e) {
            throw new IllegalArgumentException("Aucun compte trouvé avec le numéro : " + number);
        } catch (RestClientException e) {
            throw new IOException("Erreur de connexion au service distant", e);
        }
    }

    
}
