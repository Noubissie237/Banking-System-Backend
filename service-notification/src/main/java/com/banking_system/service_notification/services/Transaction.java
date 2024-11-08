package com.banking_system.service_notification.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.banking_system.service_notification.events.Retrait;
import com.banking_system.service_notification.events.TransfertEventEnvoyeur;
import com.banking_system.service_notification.events.TransfertEventRecepteur;

public class Transaction {

     @Autowired
    private RabbitTemplate rabbitTemplate;

    public void transfertEnvoyeur(TransfertEventEnvoyeur transfertEventEnvoyeur) {
        try {
            String message = "Transfert de " + transfertEventEnvoyeur.getMontant() +  " Frais " + transfertEventEnvoyeur.getFrais() + " a " + transfertEventEnvoyeur.getNumero_cible() + " effectué avec succès !";
            rabbitTemplate.convertAndSend("clientExchange", "transfertenvoyeurmessage", message);
        } catch (Exception e) {
            throw new RuntimeException("Transfert Error : ",e);
        }
    }

    public void transfertRecepteur(TransfertEventRecepteur transfertEventRecepteur) {
        try {
            String message = "Transfert de " + transfertEventRecepteur.getMontant() + " Solde ";
            rabbitTemplate.convertAndSend("clientExchange", "transfertrecepteurmessage", message);
        } catch (Exception e) {
            throw new RuntimeException("Transfert Error : ",e);
        }
    }

    public void retraitClient(Retrait retrait) {
        try {
            String message = "Retrait de" +retrait.getMontant() + " effectué avec succès ! Nouveau solde ";
            rabbitTemplate.convertAndSend("clientExchange", "retraitclientmessage", message);
        } catch (Exception e) {
            throw new RuntimeException("Retrait Creation Error : ",e);
        }
    }
// ajout du montant recu
    public void retraitRecepteurAgent(Retrait retrait) {
        try {
            String message = "Depot effectuer de" + " Montant " +retrait.getMontant() + " effectué avec succès !";
            rabbitTemplate.convertAndSend("clientExchange", "retraitagentmessage", message);
        } catch (Exception e) {
            throw new RuntimeException("Retrait Creation Error : ",e);
        }
    }

    public void depotEnvoyeur(TransfertEventEnvoyeur transfertEventEnvoyeur) {
        try {
            String message = "Depot de " + transfertEventEnvoyeur.getMontant() +  " Frais " + transfertEventEnvoyeur.getFrais() + " a " + transfertEventEnvoyeur.getNumero_cible() + " effectué avec succès !";
            rabbitTemplate.convertAndSend("clientExchange", "depotenvoyeurmessage", message);
        } catch (Exception e) {
            throw new RuntimeException("Depot Error : ",e);
        }
    }
// ajout du solde
    public void depotRecepteur(TransfertEventRecepteur transfertEventRecepteur) {
        try {
            String message = "Transfert de " + transfertEventRecepteur.getMontant() + " Solde ";
            rabbitTemplate.convertAndSend("clientExchange", "depotrecepteurmessage", message);
        } catch (Exception e) {
            throw new RuntimeException("Depot Error : ",e);
        }
    }
    
}
