package com.banking_system.service_tranfert.utils;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import com.banking_system.service_tranfert.events.Account;

@Component
public class Utils {

    public static final double FRAIS_TRANSFERT_ORANGE_MONEY_TO_ORANGE_MONEY = 0.5;
    public static final double FRAIS_TRANSFERT_ORANGE_MONEY_TO_MOBILE_MONEY = 1.9;
    public static final double FRAIS_TRANSFERT_MOBILE_MONEY_TO_MOBILE_MONEY = 1.0;
    public static final double FRAIS_TRANSFERT_MOBILE_MONEY_TO_ORANGE_MONEY = 1.5;

    
    private final RestTemplate restTemplate = new RestTemplate();

    public Account getAccount(String number) throws IOException {
        String url = "http://localhost:8079/SERVICE-ACCOUNT-MANAGEMENT/api/account/get/" + number;

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

    public double getFrais(int sourceAgenceId, int cibleAgenceId) {
        if (sourceAgenceId == 1 && cibleAgenceId == 1) {
            return FRAIS_TRANSFERT_MOBILE_MONEY_TO_MOBILE_MONEY;
        } else if (sourceAgenceId == 1 && cibleAgenceId == 2) {
            return FRAIS_TRANSFERT_MOBILE_MONEY_TO_ORANGE_MONEY;
        } else if (sourceAgenceId == 2 && cibleAgenceId == 2) {
            return FRAIS_TRANSFERT_ORANGE_MONEY_TO_ORANGE_MONEY;
        } else if (sourceAgenceId == 2 && cibleAgenceId == 1) {
            return FRAIS_TRANSFERT_ORANGE_MONEY_TO_MOBILE_MONEY;
        } else {
            throw new IllegalArgumentException("Les identifiants d'agence sont invalides.");
        }
    }

    public double getToDebit(double montant, int sourceAgenceId, int cibleAgenceId) {
        double frais = getFrais(sourceAgenceId, cibleAgenceId);
        return (montant * frais) / 100;
    }

    public static boolean isANumber(String number) {
        if (number == null || number.isEmpty()) return false;
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isBeginBy6(String number) {
        return number.startsWith("6");
    }

    public boolean isInRange(int value, int start, int end) {
        return value >= start && value <= end;
    }

    public boolean isAnOrangeNumber(String number) {        
        int debut = Integer.parseInt(number.substring(1, 3));
        return isInRange(debut, 55, 59) || isInRange(debut, 85, 99);
    }

    public boolean isAnMtnNumber(String number) {        
        int debut = Integer.parseInt(number.substring(1, 3));
        return isInRange(debut, 50, 54) || isInRange(debut, 70, 84);
    }

    public boolean isAValidNumber(String number) {
        if (!isANumber(number))
            throw new IllegalArgumentException("Le numéro de téléphone doit être constitué uniquement de chiffres");
        
        if (!isBeginBy6(number))
            throw new IllegalArgumentException("Le numéro de téléphone doit commencer par 6");

        if (!isAnMtnNumber(number) && !isAnOrangeNumber(number))
            throw new IllegalArgumentException("Numéro invalide !");

        return true;
    }

    public int getAgenceId(String numero) {
        if (isAValidNumber(numero)) {
            return isAnMtnNumber(numero) ? 1 : 2;
        }
        throw new IllegalArgumentException("Numéro de téléphone invalide.");
    }
}
