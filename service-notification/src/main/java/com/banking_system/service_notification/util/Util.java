package com.banking_system.service_notification.util;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.banking_system.service_notification.events.Account;
import com.banking_system.service_notification.events.AccountAgent;
import com.banking_system.service_notification.events.Person;
import com.banking_system.service_notification.events.Solde;

@Component
public class Util {

    private final RestTemplate restTemplate = new RestTemplate();

    public Account getEmail(String number) throws IOException {
        String url = "https://proxy.quick-send.site/SERVICE-USERS/api/get-user/" + number;

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

    public Person getPerson(String number) throws IOException {
        String url = "https://proxy.quick-send.site/SERVICE-USERS/api/get-user/" + number;

        try {
            Person person = restTemplate.getForObject(url, Person.class);
            if (person == null) {
                throw new IllegalArgumentException("Aucun compte trouvé avec le numéro : " + number);
            }
            return person;
        } catch (HttpClientErrorException.NotFound e) {
            throw new IllegalArgumentException("Aucun compte trouvé avec le numéro : " + number);
        } catch (RestClientException e) {
            throw new IOException("Erreur de connexion au service distant", e);
        }
    }

    public Account getEmailAgent(String number) throws IOException {
        String url = "https://proxy.quick-send.site/SERVICE-USERS/api/agent/get/" + number;

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

    public Account getUserEmailByNum(String number) throws IOException {
        String url = "https://proxy.quick-send.site/SERVICE-USERS/api/get-user/" + number;

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

    public AccountAgent getAgentEmailByNum(String number) throws IOException {
        String url = "https://proxy.quick-send.site/SERVICE-USERS/api/get-user/" + number;

        try {
            AccountAgent account = restTemplate.getForObject(url, AccountAgent.class);
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

    public AccountAgent getAgentEmailByMat(String matricule) throws IOException {
        String url = "https://proxy.quick-send.site/SERVICE-USERS/api/get-agent/" + matricule;

        try {
            AccountAgent account = restTemplate.getForObject(url, AccountAgent.class);
            if (account == null) {
                throw new IllegalArgumentException("Aucun compte trouvé avec le matricule : " + matricule);
            }
            return account;
        } catch (HttpClientErrorException.NotFound e) {
            throw new IllegalArgumentException("Aucun compte trouvé avec le matricule : " + matricule);
        } catch (RestClientException e) {
            throw new IOException("Erreur de connexion au service distant", e);
        }
    }

    public Solde getSoldeAgent(String number) throws IOException {
        String url = "https://proxy.quick-send.site/SERVICE-ACCOUNT-MANAGEMENT/api/account/get/" + number;

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
        String url = "https://proxy.quick-send.site/SERVICE-ACCOUNT-MANAGEMENT/api/account/get/" + number;

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

    public double getFrais(double montant, int agence) {
        return agence == 1 ? ((montant * 2) / 100) : ((montant * 1.5) / 100);
    }
}
