package com.banking_system.service_notification.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.banking_system.service_notification.events.DepotEventConsumer;
import com.banking_system.service_notification.events.RetraitEventProducer;
import com.banking_system.service_notification.events.TransfertEventEnvoyeur;
import com.banking_system.service_notification.util.Util;

public class Transaction {

    @Autowired
     Util util;

     @Autowired
    private RabbitTemplate rabbitTemplate;

    public void transfertEnvoyeur(TransfertEventEnvoyeur transfertEventEnvoyeur) {
        try {
            String message = "Transfert de " + transfertEventEnvoyeur.getNumero_source() + " vers " + transfertEventEnvoyeur.getNumero_cible() + " Reussi. Information detaillees: Montant de transaction " + transfertEventEnvoyeur.getMontant();
            rabbitTemplate.convertAndSend("clientExchange", "transfertenvoyeurmessage", message);
        } catch (Exception e) {
            throw new RuntimeException("Transfert Error : ",e);
        }
    }


    public void retraitClient(RetraitEventProducer retrait) {
        try {
            String message = "Retrait d'agent reussi par " + retrait.getMatricule_agent() + " to " +  retrait.getMatricule_agent() + ". Information detaillees: Montant de transaction " + retrait.getMontant();
            rabbitTemplate.convertAndSend("clientExchange", "retraitclientmessage", message);
        } catch (Exception e) {
            throw new RuntimeException("Retrait Creation Error : ",e);
        }
    }

    public void depotEnvoyeur(DepotEventConsumer transfertEventEnvoyeur) {
        try {
            String message = "Depot de " + transfertEventEnvoyeur.getMontant() +  " Frais 0.0 FCFA"  + " a " + transfertEventEnvoyeur.getNumero_cible() + " effectué avec succès !";
            rabbitTemplate.convertAndSend("clientExchange", "depotenvoyeurmessage", message);
        } catch (Exception e) {
            throw new RuntimeException("Depot Error : ",e);
        }
    }

    
}
