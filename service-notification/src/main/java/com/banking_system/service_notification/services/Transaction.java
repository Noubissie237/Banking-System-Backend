package com.banking_system.service_notification.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.banking_system.service_notification.events.RetraitEventProducer;
import com.banking_system.service_notification.events.RetraitProducer;
import com.banking_system.service_notification.events.Solde;
import com.banking_system.service_notification.events.TransfertEventEnvoyeur;
import com.banking_system.service_notification.events.TransfertEventRecepteur;
import com.banking_system.service_notification.util.Util;

public class Transaction {

    @Autowired
     Util util;

     @Autowired
    private RabbitTemplate rabbitTemplate;

    public void transfertEnvoyeur(TransfertEventEnvoyeur transfertEventEnvoyeur) {
        Solde sourceAccount;
        try {
            sourceAccount = util.getsoldeClient(transfertEventEnvoyeur.getNumero_source());
           
            String message = "Transfert de " + transfertEventEnvoyeur.getNumero_source() + " vers " + transfertEventEnvoyeur.getNumero_cible() + " Reussi. Information detaillees: Montant de transaction " + transfertEventEnvoyeur.getMontant() + " FCFA, Frais 0 FCFA, Commmission : 0 FCFA, Montant net du credit : " + transfertEventEnvoyeur.getNumero_cible() + transfertEventEnvoyeur.getFrais() + ", Nouveau solde : " + transfertEventEnvoyeur.getMontant() + sourceAccount.getSolde() + " FCFA.";
            rabbitTemplate.convertAndSend("clientExchange", "transfertenvoyeurmessage", message);
        } catch (Exception e) {
            throw new RuntimeException("Transfert Error : ",e);
        }
    }

    public void transfertRecepteur(TransfertEventRecepteur transfertEventRecepteur) {
        Solde sourceAccount;
        try {
            sourceAccount = util.getsoldeClient(transfertEventRecepteur.getNumero_cible());
           
            String message = "Depot effectue par " + transfertEventRecepteur.getNumero_source() + " to " + transfertEventRecepteur.getNumero_cible() + ". Information detaillees: Montant de transaction " + transfertEventRecepteur.getMontant() + " FCFA, Frais 0 FCFA, Commmission : 0 FCFA, Montant net du credit : " + transfertEventRecepteur.getNumero_cible() + ", Nouveau solde : " + transfertEventRecepteur.getMontant() + sourceAccount.getSolde() +  " FCFA.";
            rabbitTemplate.convertAndSend("clientExchange", "transfertrecepteurmessage", message);
        } catch (Exception e) {
            throw new RuntimeException("Transfert Error : ",e);
        }
    }

    public void retraitClient(RetraitEventProducer retrait) {
         Solde sourceAccount;
        try {
            sourceAccount = util.getsoldeClient(retrait.getNumero_cible());
            String message = "Retrait d'agent reussi par " + retrait.getNumero_agent() + " avec le code " +  retrait.getNumero_agent() + ". Information detaillees: Montant de transaction " + retrait.getMontant() + " FCFA, Frais 0 FCFA, Commmission : 0 FCFA, Montant net du credit : " + retrait.getMontant() + retrait.getFrais() + ", Nouveau solde : " + retrait.getMontant() + sourceAccount.getSolde() + " FCFA.";
            rabbitTemplate.convertAndSend("clientExchange", "retraitclientmessage", message);
        } catch (Exception e) {
            throw new RuntimeException("Retrait Creation Error : ",e);
        }
    }
// ajout du montant recu
    public void retraitRecepteurAgent(RetraitProducer retrait) {
        Solde sourceAccount;
        try {
            sourceAccount = util.getSoldeAgent(retrait.getNumero_agent());
            String message = "Depot effectue par " + retrait.getNumero_cible() + " to " + retrait.getNumero_agent()+ ". Information detaillees: Montant de transaction " + retrait.getMontant() + " FCFA, Frais 0 FCFA, Commmission : 0 FCFA, Montant net du credit : " + retrait.getMontant() + ", Nouveau solde : " + retrait.getMontant() + sourceAccount.getSolde() + " FCFA.";
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
