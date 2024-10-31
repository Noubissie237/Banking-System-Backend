package com.banking_system.service_users.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.banking_system.service_users.repositories.AgentRepository;


@Component
public class Utils {
    
    @Autowired
    AgentRepository agentRepository;

    private final Random random = new Random();

    public String generateUniqueMatricule() {
        String matricule;
        do {
            matricule = generateMatricule();
        }
        while(agentRepository.findByMatricule(matricule) != null);
        return matricule;
    }

    public String generateMatricule() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yy");
        String annee = dtf.format(now);

        String jour = generateDayCarac(String.valueOf(now.getDayOfWeek().getValue()));
        String heure = String.format("%02d", now.getHour());
        String minute = String.format("%02d", now.getMinute());
        String carac = generateRandomChars(2);
        return annee + jour + heure + minute + carac;
    }

    public String generateRandomChars(int taille) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<taille; i++) {
            sb.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        return sb.toString();
    }

    public String generateDayCarac(String jour) {
        String jourCarac = "";
        switch(jour) {
            case "1":
                jourCarac = "LU";
                break;
            case "2":
                jourCarac = "MA";
                break;
            case "3":
                jourCarac = "ME";
                break;
            case "4":
                jourCarac = "JE";
                break;
            case "5":
                jourCarac = "VE";
                break;
            case "6":
                jourCarac = "SA";
                break;
            case "7":
                jourCarac = "DI";
                break;
            default:
                throw new RuntimeException("Jour inconnu !");
        }
        return jourCarac;
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
