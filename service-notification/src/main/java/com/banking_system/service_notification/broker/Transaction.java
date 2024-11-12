package com.banking_system.service_notification.broker;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.banking_system.service_notification.events.Retrait;
import com.banking_system.service_notification.events.TransfertEventEnvoyeur;
import com.banking_system.service_notification.events.TransfertEventRecepteur;

@Component
public class Transaction{

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

    @RabbitListener(queues = "retraitmessageQueue")
    public void retraitClient(Retrait retrait) {
        try {
            String message = "Retrait d'agent reussi par " + retrait.getNumero_source() + " . Informations detaillees: Montant: " +retrait.getMontant() + " Nouveau solde : ";
            System.out.println(message);
        } catch (Exception e) {
            throw new RuntimeException("Retrait Creation Error : ",e);
        }
    }

    @RabbitListener(queues = "retraitmessageQueue")
    public void retraitRecepteurAgent(Retrait retrait) {
        try {
            String message = "Depot effectue par " + retrait.getNumero_source() + " to " + retrait.getNumero_cible() + ". Information detaillees: Montant de transaction " + retrait.getMontant() + " FCFA, Frais 0 FCFA, Commmission : 0 FCFA, Montant net du credit : " + retrait.getNumero_cible() + ", Nouveau solde : " + " FCFA.";
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
