package com.banking_system.service_notification.services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.banking_system.service_notification.events.Account;
import com.banking_system.service_notification.events.AccountAgent;
import com.banking_system.service_notification.events.RetraitProducer;
import com.banking_system.service_notification.models.Notification;
import com.banking_system.service_notification.repositories.Repository;
import com.banking_system.service_notification.util.Util;

import jakarta.mail.MessagingException;

@Service
public class NotificationService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    Util util;

    @Autowired
    private EmailSender mailservice;

    @Autowired
    Repository notificationRepository;

    public void askRetrait(RetraitProducer retrait) throws IOException, MessagingException {
        String agentUrl = "http://localhost:8079/SERVICE-USERS/api/get-agent/" + retrait.getMatricule_agent();
        String clientUrl = "http://localhost:8079/SERVICE-USERS/api/client/get/" + retrait.getNumero_cible();

        try {
            AccountAgent agent = restTemplate.getForObject(agentUrl, AccountAgent.class);
            Account client = restTemplate.getForObject(clientUrl, Account.class);

            if (client == null) {
                throw new IllegalArgumentException("Compte client introuvable");
            } else if (agent == null) {
                throw new IllegalArgumentException("Compte agent introuvable");
            } else {

                String message = "Une demande de retrait de " + retrait.getMontant() + " FCFA, frais "
                        + util.getFrais(retrait.getMontant(), retrait.getAgence())
                        + " FCFA à été lancé par l'agent " + agent.getNom().toUpperCase() + " "
                        + agent.getPrenom().toUpperCase()
                        + " MATRICULE " + agent.getMatricule() + "<br><br>"
                        + " Veuillez fournir le code à usage unique ci dessous à l'agent pour confirmer la transaction<br><br>"
                        + "<a href='#' style='padding:10px 20px; background-color:green; color:white; text-decoration:none; border-radius:5px;'>"+retrait.getPass()+"</a> ";

                mailservice.sendMail(client.getEmail(), "Demande de retrait", message);
            }
        } catch (RestClientException e) {
            throw new IOException("Erreur de connexion au service distant", e);
        } catch (Exception e) {
            throw new RuntimeException("Retrait Query Error : ", e);
        }
    }

    public void sendNotification(Notification notif) {
        notif.setDate(LocalDateTime.now());
        notificationRepository.save(notif);
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public List<Notification> getAllNotificationsUser(String number) {
        return notificationRepository.findByDestinataire(number);
    }
}
