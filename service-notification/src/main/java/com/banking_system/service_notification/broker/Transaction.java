package com.banking_system.service_notification.broker;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.banking_system.service_notification.events.Account;
import com.banking_system.service_notification.events.AccountAgent;
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

    @RabbitListener(queues = "transfertDoneForMessageQueue")
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
            String message = "Transfert de vous"
                    + " vers " + transfertEventEnvoyeur.getNumero_cible() + " "
                    + mail2.getNom().toUpperCase() + " " + mail2.getPrenom().toUpperCase()
                    + " Reussi. Information detaillees: Montant de transaction "
                    + transfertEventEnvoyeur.getMontant()
                    + " FCFA, Frais " + transfertEventEnvoyeur.getFrais()
                    + " FCFA, Commmission : 0 FCFA, Montant net du credit : "
                    + (transfertEventEnvoyeur.getMontant() + transfertEventEnvoyeur.getFrais())
                    + " FCFA, Nouveau solde : " + sourceAccount.getSolde()
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
            String message = "Transfert effectué par " + transfertEventRecepteur.getNumero_source() + " "
                    + mail2.getNom().toUpperCase() + " vers vous "
                    + mail.getNom().toUpperCase() + ". Information detaillees: Montant de transaction "
                    + transfertEventRecepteur.getMontant()
                    + " FCFA, Frais 0 FCFA, Commmission : 0 FCFA, Montant net du credit : "
                    + transfertEventRecepteur.getMontant() + " FCFA, Nouveau solde : "
                    + sourceAccount.getSolde() + " FCFA.";
            mailservice.sendMail(mail.getEmail(), "Transfert d'agent", message);

            System.out.println(message);
        } catch (Exception e) {
            throw new RuntimeException("Transfert Error : ", e);
        }
    }

    @RabbitListener(queues = "retraitDoneForMessageQueue")
    public void retrait(RetraitEventProducer retrait) {
        retraitAgent(retrait);
        retraitClient(retrait);
    }

    public void retraitClient(RetraitEventProducer retrait) {
        Solde sourceAccount;
        Account client;
        AccountAgent agent;
        try {
            client = util.getEmail(retrait.getNumero_cible());
            agent = util.getAgentEmailByMat(retrait.getMatricule_agent());
            sourceAccount = util.getSoldeAgent(client.getTel());
            String message = "Retrait effectué de vous "
                    + client.getNom().toUpperCase() + " à l'agent " +agent.getMatricule()+ " "
                    + agent.getNom().toUpperCase() + ". Information detaillees: Montant de transaction "
                    + retrait.getMontant() + " FCFA, Frais "+ util.getFrais(retrait.getMontant(), retrait.getAgence()) +" FCFA, Commmission : 0 FCFA, Montant net du credit : "
                    + (retrait.getMontant() + retrait.getFrais()) + " FCFA, Nouveau solde : "
                    + (sourceAccount.getSolde()) + " FCFA.";
            mailservice.sendMail(client.getEmail(), "Retrait d'agent", message);
            System.out.println(message);
        } catch (Exception e) {
            throw new RuntimeException("Retrait Creation Error : ", e);
        }
    }

    public void retraitAgent(RetraitEventProducer retrait) {
        Solde sourceAccount;
        Account client;
        AccountAgent agent;
        try {
            client = util.getEmail(retrait.getNumero_cible());
            agent = util.getAgentEmailByMat(retrait.getMatricule_agent());
            sourceAccount = util.getsoldeClient(agent.getTel());
            String message = "Retrait effectué du client " + client.getTel() + " "
                    + client.getNom().toUpperCase() + " à vous "
                    + agent.getNom().toUpperCase() + ". Information detaillees: Montant de transaction "
                    + retrait.getMontant() + " FCFA, Frais "+ util.getFrais(retrait.getMontant(), retrait.getAgence()) +" FCFA, Commmission : "+ ((util.getFrais(retrait.getMontant(), retrait.getAgence())) * 0.25) +" FCFA, Montant net du credit : "
                    + (retrait.getMontant() + retrait.getFrais()) + " FCFA, Nouveau solde : "
                    + (sourceAccount.getSolde()) + " FCFA.";
            mailservice.sendMail(agent.getEmail(), "Retrait d'agent", message);
            System.out.println(message);
        } catch (Exception e) {
            throw new RuntimeException("Retrait Creation Error : ", e);
        }
    }

    @RabbitListener(queues = "depotDoneForMessageQueue")
    public void depot(DepotEventConsumer depot) {
        depotEnvoyeur(depot);
        depotRecepteur(depot);
    }

    public void depotEnvoyeur(DepotEventConsumer depot) {
        Solde sourceAccount;
        Account mail;
        Account mail2;
        try {
            sourceAccount = util.getsoldeClient(depot.getNumero_source());
            mail = util.getUserEmailByNum(depot.getNumero_source());
            mail2 = util.getEmail(depot.getNumero_cible());
            String message = "Dépôt de vous "
                    + " vers " + depot.getNumero_cible() + " "
                    + mail2.getNom().toUpperCase() + " Reussi. Information detaillees: Montant de transaction "
                    + depot.getMontant()
                    + " FCFA, Frais 0 FCFA, Commmission : 0 FCFA, Montant net du credit : "
                    + depot.getMontant() + " FCFA, Nouveau solde : "
                    + (sourceAccount.getSolde()) + " FCFA.";
            mailservice.sendMail(mail.getEmail(), "Dépôt d'argent", message);

            System.out.println(message);
        } catch (Exception e) {
            throw new RuntimeException("Dépôt Error : ", e);
        }
    }

    public void depotRecepteur(DepotEventConsumer depot) {
        Solde sourceAccount;
        Account mail;
        Account mail2;
        try {
            sourceAccount = util.getsoldeClient(depot.getNumero_cible());
            mail = util.getEmail(depot.getNumero_cible());
            mail2 = util.getUserEmailByNum(depot.getNumero_source());
            String message = "Dépoôt effectuée par " + depot.getNumero_source() + " "
                    + mail2.getNom().toUpperCase() + " vers vous"
                    + ". Information detaillees: Montant de transaction "
                    + depot.getMontant()
                    + " FCFA, Frais 0 FCFA, Commmission : 0 FCFA, Montant net du credit : "
                    + depot.getMontant() + " FCFA, Nouveau solde : "
                    + (sourceAccount.getSolde()) + " FCFA.";
            mailservice.sendMail(mail.getEmail(), "Dépôt d'agent", message);

            System.out.println(message);
        } catch (Exception e) {
            throw new RuntimeException("Dépôt Error : ", e);
        }
    }

    @RabbitListener(queues = "rechargeDoneForMessageQueue")
    public void rechargeByAgence(RechargeEventConsumer rechargeEventConsumer) {
        Solde sourceAccount;
        Account mail;

        try {
            sourceAccount = util.getSoldeAgent(rechargeEventConsumer.getNumero());
            mail = util.getUserEmailByNum(rechargeEventConsumer.getNumero());
            String agence = rechargeEventConsumer.getAgence() == 1 ? "MTN Mobile Money" : "Orange Money OM";
            String message = "Recharge effectuée par " + agence + " à "
                    + rechargeEventConsumer.getNumero() + " " + mail.getNom().toUpperCase()
                    + " " + mail.getPrenom().toUpperCase() + ". Information detaillees: Montant de transaction "
                    + rechargeEventConsumer.getMontant()
                    + " FCFA, Frais 0 FCFA, Commmission : 0 FCFA, Montant net du credit : "
                    + rechargeEventConsumer.getMontant() + " FCFA, Nouveau solde : "
                    + (sourceAccount.getSolde()) + " FCFA.";

            mailservice.sendMail(mail.getEmail(), "Recharge d'agent", message);
            System.out.println(message);
        } catch (Exception e) {
            throw new RuntimeException("Recharge Error : ", e);
        }
    }

}
