package com.banking_system.service_notification.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking_system.service_notification.events.AgentEvent;


@Service
public class AccountService {
    
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void createAccount() {
        try {
            String message = "Compte créé avec succès ! Vous pouvez à présent profiter de nos services 😀 ";
            rabbitTemplate.convertAndSend("clientExchange", "client-account-create", message);
        } catch (Exception e) {
            throw new RuntimeException("Account Creation Error : ",e);
        }
    }

    public void createAgentAccount(AgentEvent event) {
        try {
            String message = "Compte Agent créé avec succès ! \n Votre numero est " +event.getNumero() + "\n Matricule " +event.getMatricule() + "\n Vous pouvez à présent profiter de nos services 😀 ";
            rabbitTemplate.convertAndSend("clientExchange", "agent-account-create", message);
        } catch (Exception e) {
            throw new RuntimeException("Account Creation Error : ",e);
        }
    }

    public void createRejetAccount() {
        try {
            String message = "Echec de creation du Compte ! <br> Veillez vous rapporcher de l'agence.<br><br> Cordialement,";
            rabbitTemplate.convertAndSend("clientExchange", "account.rejet", message);
        } catch (Exception e) {
            throw new RuntimeException("Account Creation Error : ",e);
        }
    }

    
    

}
