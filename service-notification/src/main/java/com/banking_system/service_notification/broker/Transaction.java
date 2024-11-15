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
        try {
            String message = "Transfert de " + transfertEventEnvoyeur.getNumero_source() + " vers " + transfertEventEnvoyeur.getNumero_cible() + " Reussi. Information detaillees: Montant de transaction " + transfertEventEnvoyeur.getMontant() + " FCFA, Frais 0 FCFA, Commmission : 0 FCFA, Montant net du credit : " + transfertEventEnvoyeur.getNumero_cible() + ", Nouveau solde : " + " FCFA.";
            System.out.println(message);
        } catch (Exception e) {
            throw new RuntimeException("Transfert Error : ",e);
        }
    }

    @RabbitListener(queues = "transfertMoneyQueue")
    public void transfertRecepteur(TransfertEventRecepteur transfertEventRecepteur) {
        try {
            String message = "Depot effectue par " + transfertEventRecepteur.getNumero_source() + " to " + transfertEventRecepteur.getNumero_cible() + ". Information detaillees: Montant de transaction " + transfertEventRecepteur.getMontant() + " FCFA, Frais 0 FCFA, Commmission : 0 FCFA, Montant net du credit : " + transfertEventRecepteur.getNumero_cible() + ", Nouveau solde : " + " FCFA.";
            System.out.println(message);
        } catch (Exception e) {
            throw new RuntimeException("Transfert Error : ",e);
        }
    }

    @RabbitListener(queues = "retraitMoneyQueueForTransactions")
    public void retraitClient(RetraitEventProducer retrait) {
        
        Solde sourceAccount;
        Account mail;

        try {
            sourceAccount = util.getsoldeClient(retrait.getNumero_cible());
            mail = util.getEmail(retrait.getNumero_cible());
           
            String message = "Retrait d'agent reussi par " + retrait.getNumero_agent() + " avec le code " +  retrait.getNumero_agent() + ". Information detaillees: Montant de transaction " + retrait.getMontant() + " FCFA, Frais 0 FCFA, Commmission : 0 FCFA, Montant net du credit : " + retrait.getMontant() + retrait.getFrais() + ", Nouveau solde : " + retrait.getMontant() + sourceAccount.getSolde() + " FCFA.";
            mailservice.sendMail(mail.getEmail(),"Confirmation de création de compte", message);
    
            System.out.println(message);
        } catch (Exception e) {
            throw new RuntimeException("Retrait Creation Error : ",e);
        }
    }

    @RabbitListener(queues = "retraitAgenceQueue")
    public void retraitRecepteurAgent(RetraitProducer retrait) {

        Solde sourceAccount;
        Account mail;
        try {
            sourceAccount = util.getSoldeAgent(retrait.getNumero_agent());
            mail = util.getEmailAgent(retrait.getNumero_agent());
           
            String message = "Depot effectue par " + retrait.getNumero_cible() + " to " + retrait.getNumero_agent()+ ". Information detaillees: Montant de transaction " + retrait.getMontant() + " FCFA, Frais 0 FCFA, Commmission : 0 FCFA, Montant net du credit : " + retrait.getMontant() + ", Nouveau solde : " + retrait.getMontant() + sourceAccount.getSolde() + " FCFA.";
            
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
