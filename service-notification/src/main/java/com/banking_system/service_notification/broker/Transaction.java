package com.banking_system.service_notification.broker;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.banking_system.service_notification.events.Account;
import com.banking_system.service_notification.events.RetraitEventProducer;
import com.banking_system.service_notification.events.RetraitProducer;
import com.banking_system.service_notification.events.Solde;
import com.banking_system.service_notification.events.TransfertEventEnvoyeur;
import com.banking_system.service_notification.events.TransfertEventRecepteur;
import com.banking_system.service_notification.services.EmailSender;
import com.banking_system.service_notification.util.Util;

@Component
public class Transaction{
        @Autowired
        Util util;

        @Autowired
        private EmailSender mailservice;


    @RabbitListener(queues = "transfertMoneyQueue")
    public void transfertEnvoyeur(TransfertEventEnvoyeur transfertEventEnvoyeur) {
        Solde sourceAccount;
        Account mail;
        Account mail2;
        try {
            sourceAccount = util.getsoldeClient(transfertEventEnvoyeur.getNumero_source());
            mail = util.getEmail(transfertEventEnvoyeur.getNumero_source());
            mail2 = util.getEmail(transfertEventEnvoyeur.getNumero_cible());
            String message = "Transfert de " + transfertEventEnvoyeur.getNumero_source() + " " + mail.getNom() + " vers " + transfertEventEnvoyeur.getNumero_cible() + " " + mail2.getNom() + " Reussi. Information detaillees: Montant de transaction " + transfertEventEnvoyeur.getMontant() + " FCFA, Frais 0 FCFA, Commmission : 0 FCFA, Montant net du credit : " + transfertEventEnvoyeur.getNumero_cible() + transfertEventEnvoyeur.getFrais() + ", Nouveau solde : " + transfertEventEnvoyeur.getMontant() + sourceAccount.getSolde() + " FCFA.";
            mailservice.sendMail(mail.getEmail(),"Transfert d'argent", message);
    
            System.out.println(message);
        } catch (Exception e) {
            throw new RuntimeException("Transfert Error : ",e);
        }
    }

    @RabbitListener(queues = "transfertMoneyQueue")
    public void transfertRecepteur(TransfertEventRecepteur transfertEventRecepteur) {
        Solde sourceAccount;
        Account mail;
        Account mail2;
        try {
            sourceAccount = util.getsoldeClient(transfertEventRecepteur.getNumero_cible());
            mail = util.getEmail(transfertEventRecepteur.getNumero_cible());
            mail2 = util.getEmail(transfertEventRecepteur.getNumero_source());
            String message = "Depot effectue par " + transfertEventRecepteur.getNumero_source() + " " + mail2.getNom() +  " to " + transfertEventRecepteur.getNumero_cible() + mail.getNom() + ". Information detaillees: Montant de transaction " + transfertEventRecepteur.getMontant() + " FCFA, Frais 0 FCFA, Commmission : 0 FCFA, Montant net du credit : " + transfertEventRecepteur.getNumero_cible() + ", Nouveau solde : " + transfertEventRecepteur.getMontant() + sourceAccount.getSolde() +  " FCFA.";
            mailservice.sendMail(mail.getEmail(),"Depot d'agent", message);
    
            System.out.println(message);
        } catch (Exception e) {
            throw new RuntimeException("Transfert Error : ",e);
        }
    }

    @RabbitListener(queues = "retraitMoneyQueueForTransactions")
    public void retraitClient(RetraitEventProducer retrait) {
        
        Solde sourceAccount;
        Account mail;
        Account mail2;
        try {
            sourceAccount = util.getsoldeClient(retrait.getNumero_cible());
            mail = util.getEmail(retrait.getNumero_cible());
            mail2 = util.getEmail(retrait.getNumero_agent());
            String message = "Retrait d'agent reussi par " + retrait.getNumero_agent() + " " + mail.getNom() + " to " +  retrait.getNumero_agent() + " " + mail2.getNom() + ". Information detaillees: Montant de transaction " + retrait.getMontant() + " FCFA, Frais 0 FCFA, Commmission : 0 FCFA, Montant net du credit : " + retrait.getMontant() + retrait.getFrais() + ", Nouveau solde : " + retrait.getMontant() + sourceAccount.getSolde() + " FCFA.";
            mailservice.sendMail(mail.getEmail(),"Retrait d'agent", message);
    
            System.out.println(message);
        } catch (Exception e) {
            throw new RuntimeException("Retrait Creation Error : ",e);
        }
    }

    @RabbitListener(queues = "retraitAgenceQueue")
    public void retraitRecepteurAgent(RetraitProducer retrait) {

        Solde sourceAccount;
        Account mail;
        Account mail2;
        try {
            sourceAccount = util.getSoldeAgent(retrait.getNumero_agent());
            mail = util.getEmailAgent(retrait.getNumero_agent());
            mail2 = util.getEmail(retrait.getNumero_cible());
            String message = "Depot effectue par " + retrait.getNumero_cible() + " " + mail2.getNom() + " to " + retrait.getNumero_agent() + " " + mail.getNom() + ". Information detaillees: Montant de transaction " + retrait.getMontant() + " FCFA, Frais 0 FCFA, Commmission : 0 FCFA, Montant net du credit : " + retrait.getMontant() + ", Nouveau solde : " + retrait.getMontant() + sourceAccount.getSolde() + " FCFA.";
            
            System.out.println(message);
        } catch (Exception e) {
            throw new RuntimeException("Retrait Creation Error : ",e);
        }
    }

    @RabbitListener(queues = "depotmessageQueue")
    public void depotEnvoyeur(TransfertEventEnvoyeur transfertEventEnvoyeur) {
        try {
            String message = "Depot effectue par " + transfertEventEnvoyeur.getNumero_source() + " to " + transfertEventEnvoyeur.getNumero_cible() + ". Information detaillees: Montant de transaction " + transfertEventEnvoyeur.getMontant() + " FCFA, Frais 0 FCFA, Commmission : 0 FCFA, Montant net du credit : " + transfertEventEnvoyeur.getNumero_cible() + ", Nouveau solde : " + " FCFA.";
            System.out.println(message);    
        } catch (Exception e) {
            throw new RuntimeException("Depot Error : ",e);
        }
    }

    @RabbitListener(queues = "depotmessageQueue")
    public void depotRecepteur(TransfertEventRecepteur transfertEventRecepteur) {
        try {
            String message = "Transfert de " + transfertEventRecepteur.getNumero_source() + " vers " + transfertEventRecepteur.getNumero_cible() + ". Information detaillees: Montant de transaction " + transfertEventRecepteur.getMontant() + " FCFA, Frais 0 FCFA, Commmission : 0 FCFA, Montant net du credit : " + transfertEventRecepteur.getNumero_cible() + ", Nouveau solde : " + " FCFA.";
            System.out.println(message);
        } catch (Exception e) {
            throw new RuntimeException("Depot Error : ",e);
        }
    }


}
