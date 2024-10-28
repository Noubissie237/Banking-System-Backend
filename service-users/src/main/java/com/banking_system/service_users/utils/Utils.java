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

}
