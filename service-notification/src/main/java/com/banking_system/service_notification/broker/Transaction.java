package com.banking_system.service_notification.broker;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.banking_system.service_notification.events.Account;
import com.banking_system.service_notification.events.DepotEventConsumer;
import com.banking_system.service_notification.events.RechargeEventConsumer;
import com.banking_system.service_notification.events.RetraitEventProducer;
import com.banking_system.service_notification.events.Solde;
import com.banking_system.service_notification.events.TransfertEventEnvoyeur;
import com.banking_system.service_notification.services.EmailSender;
import com.banking_system.service_notification.util.Util;

@Component
public class Transaction {
    @Autowired
    Util util;

    @Autowired
    private EmailSender mailservice;

    @RabbitListener(queues = "transfertm")
    public void transfert(TransfertEventEnvoyeur transfertEventEnvoyeur) {
        transfertEnvoyeur(transfertEventEnvoyeur);
        transfertRecepteur(transfertEventEnvoyeur);
    }

    public void transfertEnvoyeur(TransfertEventEnvoyeur transfertEventEnvoyeur) {
        Solde sourceAccount;
        Account mail;
        Account mail2;
        try {
            sourceAccount = util.getsoldeClient(transfertEventEnvoyeur.getNumero_source());
            mail = util.getEmail(transfertEventEnvoyeur.getNumero_source());
            mail2 = util.getEmail(transfertEventEnvoyeur.getNumero_cible());
            String message = "Transfert de " + transfertEventEnvoyeur.getNumero_source() + " "
                    + mail.getNom().toUpperCase() + " vers " + transfertEventEnvoyeur.getNumero_cible() + " "
                    + mail2.getNom().toUpperCase() + " Reussi. Information detaillees: Montant de transaction "
                    + transfertEventEnvoyeur.getMontant()
                    + " FCFA, Frais 0 FCFA, Commmission : 0 FCFA, Montant net du credit : "
                    + (transfertEventEnvoyeur.getMontant() + transfertEventEnvoyeur.getFrais())
                    + " FCFA, Nouveau solde : " + (transfertEventEnvoyeur.getMontant() + sourceAccount.getSolde())
                    + " FCFA.";
            mailservice.sendMail(mail.getEmail(), "Transfert d'argent", message);

            System.out.println(message);
        } catch (Exception e) {
            throw new RuntimeException("Transfert Error : ", e);
        }
    }

    public void transfertRecepteur(TransfertEventEnvoyeur transfertEventRecepteur) {
        Solde sourceAccount;
        Account mail;
        Account mail2;
        try {
            sourceAccount = util.getsoldeClient(transfertEventRecepteur.getNumero_cible());
            mail = util.getEmail(transfertEventRecepteur.getNumero_cible());
            mail2 = util.getEmail(transfertEventRecepteur.getNumero_source());
            String message = "Depot effectue par " + transfertEventRecepteur.getNumero_source() + " "
                    + mail2.getNom().toUpperCase() + " to " + transfertEventRecepteur.getNumero_cible() + " "
                    + mail.getNom().toUpperCase() + ". Information detaillees: Montant de transaction "
                    + transfertEventRecepteur.getMontant()
                    + " FCFA, Frais 0 FCFA, Commmission : 0 FCFA, Montant net du credit : "
                    + transfertEventRecepteur.getMontant() + " FCFA, Nouveau solde : "
                    + (transfertEventRecepteur.getMontant() + sourceAccount.getSolde()) + " FCFA.";
            mailservice.sendMail(mail.getEmail(), "Depot d'agent", message);

            System.out.println(message);
        } catch (Exception e) {
            throw new RuntimeException("Transfert Error : ", e);
        }
    }

    @RabbitListener(queues = "retraitm")
    public void retrait(RetraitEventProducer retrait) {
        retraitClient(retrait);
        retraitConfim(retrait);
        retraitRecepteurAgent(retrait);
    }

    public void retraitClient(RetraitEventProducer retrait) {

        Solde sourceAccount;
        Account mail;
        Account mail2;
        try {
            sourceAccount = util.getsoldeClient(retrait.getNumero_cible());
            mail = util.getEmail(retrait.getNumero_cible());
            mail2 = util.getUserEmailByNum(retrait.getNumero_agent());
            String message = "Retrait d'agent reussi par " + retrait.getNumero_agent() + " "
                    + mail.getNom().toUpperCase() + " to " + retrait.getNumero_agent() + " "
                    + mail2.getNom().toUpperCase() + ". Information detaillees: Montant de transaction "
                    + retrait.getMontant() + " FCFA, Frais 0 FCFA, Commmission : 0 FCFA, Montant net du credit : "
                    + (retrait.getMontant() + retrait.getFrais()) + " FCFA, Nouveau solde : "
                    + (retrait.getMontant() + sourceAccount.getSolde()) + " FCFA.";
            mailservice.sendMail(mail.getEmail(), "Retrait d'agent", message);
            System.out.println(message);
        } catch (Exception e) {
            throw new RuntimeException("Retrait Creation Error : ", e);
        }
    }

    public void retraitRecepteurAgent(RetraitEventProducer retrait) {

        Solde sourceAccount;
        Account mail;
        Account mail2;
        try {
            sourceAccount = util.getSoldeAgent(retrait.getNumero_agent());
            mail = util.getUserEmailByNum(retrait.getNumero_agent());
            mail2 = util.getEmail(retrait.getNumero_cible());
            String message = "Depot effectue par " + retrait.getNumero_cible() + " " + mail2.getNom().toUpperCase()
                    + " to " + retrait.getNumero_agent() + " " + mail.getNom().toUpperCase()
                    + ". Information detaillees: Montant de transaction " + retrait.getMontant()
                    + " FCFA, Frais 0 FCFA, Commmission : 0 FCFA, Montant net du credit : " + retrait.getMontant()
                    + "FCFA, Nouveau solde : " + (retrait.getMontant() + sourceAccount.getSolde()) + " FCFA.";
            mailservice.sendMail(mail.getEmail(), "Depot d'argent", message);

            System.out.println(message);
        } catch (Exception e) {
            throw new RuntimeException("Retrait Creation Error : ", e);
        }
    }

    public void retraitConfim(RetraitEventProducer retrait) {

        Account mail;
        Account mail2;
        try {
            mail = util.getUserEmailByNum(retrait.getNumero_agent());
            mail2 = util.getEmail(retrait.getNumero_cible());
            String message = "Vous souhaitez faire un retrait par " + retrait.getNumero_agent() + " "
                    + mail.getNom().toUpperCase() + ". Montant de transaction " + retrait.getMontant()
                    + " via Quick Send. Veuillez confirmer en entrant votre code secret.";
            mailservice.sendMail(mail2.getEmail(), "Confirmation ", message);

            System.out.println(message);
        } catch (Exception e) {
            throw new RuntimeException("Retrait Creation Error : ", e);
        }
    }

    @RabbitListener(queues = "depotMoney")
    public void depot(DepotEventConsumer transfertEventEnvoyeur) {
        depotEnvoyeur(transfertEventEnvoyeur);
        depotRecepteur(transfertEventEnvoyeur);
    }

    public void depotEnvoyeur(DepotEventConsumer transfertEventEnvoyeur) {
        Solde sourceAccount;
        Account mail;
        Account mail2;
        try {
            sourceAccount = util.getsoldeClient(transfertEventEnvoyeur.getNumero_source());
            mail = util.getUserEmailByNum(transfertEventEnvoyeur.getNumero_source());
            mail2 = util.getEmail(transfertEventEnvoyeur.getNumero_cible());
            String message = "Transfert de " + transfertEventEnvoyeur.getNumero_source() + " "
                    + mail.getNom().toUpperCase() + " vers " + transfertEventEnvoyeur.getNumero_cible() + " "
                    + mail2.getNom().toUpperCase() + " Reussi. Information detaillees: Montant de transaction "
                    + transfertEventEnvoyeur.getMontant()
                    + " FCFA, Frais 0 FCFA, Commmission : 0 FCFA, Montant net du credit : "
                    + transfertEventEnvoyeur.getMontant() + " FCFA, Nouveau solde : "
                    + (transfertEventEnvoyeur.getMontant() + sourceAccount.getSolde()) + " FCFA.";
            mailservice.sendMail(mail.getEmail(), "Transfert d'argent", message);

            System.out.println(message);
        } catch (Exception e) {
            throw new RuntimeException("Transfert Error : ", e);
        }
    }

    public void depotRecepteur(DepotEventConsumer transfertEventRecepteur) {
        Solde sourceAccount;
        Account mail;
        Account mail2;
        try {
            sourceAccount = util.getsoldeClient(transfertEventRecepteur.getNumero_cible());
            mail = util.getEmail(transfertEventRecepteur.getNumero_cible());
            mail2 = util.getUserEmailByNum(transfertEventRecepteur.getNumero_source());
            String message = "Depot effectue par " + transfertEventRecepteur.getNumero_source() + " "
                    + mail2.getNom().toUpperCase() + " to " + transfertEventRecepteur.getNumero_cible() + " "
                    + mail.getNom().toUpperCase() + ". Information detaillees: Montant de transaction "
                    + transfertEventRecepteur.getMontant()
                    + " FCFA, Frais 0 FCFA, Commmission : 0 FCFA, Montant net du credit : "
                    + transfertEventRecepteur.getMontant() + " FCFA, Nouveau solde : "
                    + (transfertEventRecepteur.getMontant() + sourceAccount.getSolde()) + " FCFA.";
            mailservice.sendMail(mail.getEmail(), "Depot d'agent", message);

            System.out.println(message);
        } catch (Exception e) {
            throw new RuntimeException("Transfert Error : ", e);
        }
    }

    @RabbitListener(queues = "rechargeByAgenceo")
    public void rechargeByAgence(RechargeEventConsumer rechargeEventConsumer) {
        Solde sourceAccount;
        Account mail;

        try {
            sourceAccount = util.getSoldeAgent(rechargeEventConsumer.getNumero());
            mail = util.getUserEmailByNum(rechargeEventConsumer.getNumero());
            String agence = rechargeEventConsumer.getAgence() == 1 ? "MTN Mobile Money" : "Orange Money OM";
            String message = "Recharge effectuée par " + agence + " à "
                    + rechargeEventConsumer.getNumero() + " " + mail.getNom().toUpperCase()
                    + " " + mail.getPrenom().toUpperCase() +". Information detaillees: Montant de transaction " + rechargeEventConsumer.getMontant()
                    + " FCFA, Frais 0 FCFA, Commmission : 0 FCFA, Montant net du credit : " 
                    + rechargeEventConsumer.getMontant() + " FCFA, Nouveau solde : "
                    + (rechargeEventConsumer.getMontant() + sourceAccount.getSolde()) + " FCFA.";
            
            mailservice.sendMail(mail.getEmail(), "Recharge d'agent", message);
            System.out.println(message);
        } catch (Exception e) {
            throw new RuntimeException("Transfert Error : ", e);
        }
    }

}
