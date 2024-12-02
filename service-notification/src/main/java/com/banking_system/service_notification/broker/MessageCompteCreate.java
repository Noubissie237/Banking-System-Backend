package com.banking_system.service_notification.broker;

import java.io.IOException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.banking_system.service_notification.dto.User;
import com.banking_system.service_notification.events.Account;
import com.banking_system.service_notification.events.AgentEvent;
import com.banking_system.service_notification.events.ClientAccountCreated;
import com.banking_system.service_notification.services.EmailSender;
import com.banking_system.service_notification.util.Util;

import jakarta.mail.MessagingException;

@Component
public class MessageCompteCreate {

    @Autowired
    private EmailSender mailservice;

    @Autowired
    Util util;

    @Autowired
    private RestTemplate restTemplate;

    @RabbitListener(queues = "clientAccountCreateForMessageQueue")
    public void accountCreatedClient(ClientAccountCreated event) {
        String link = "https://proxy.quick-send.site/SERVICE-USERS/api/get-user/" + event.getNumeroClient();

        try {
            User user = restTemplate.getForObject(link, User.class);

            if (user == null) {
                throw new IllegalArgumentException(
                        "Utilisateur introuvable pour le numéro fourni : " + event.getNumeroClient());
            }

            Account sourceAccount = util.getEmail(event.getNumeroClient());
            if (sourceAccount == null || sourceAccount.getEmail() == null) {
                throw new IllegalArgumentException(
                        "Aucune adresse email trouvée pour l'utilisateur : " + event.getNumeroClient());
            }

            String message = String.format(
                    "Bienvenue M./Mme %s, <br><br>Votre compte a été créé avec succès !<br>" +
                            "Vous pouvez dès à présent accéder à nos services.<br><br>" +
                            "Cordialement,<br>L'équipe de support.<br><br>"+
                            "Consultez notre site <a href ='https://quick-send.site'>ici</a><br><br>"+
                            "<a href ='https://wa.me/+237690232120'>Contact administrateur</a>",
                    user.getNom().toUpperCase());

            mailservice.sendMail(
                    sourceAccount.getEmail(),
                    "Confirmation de création de compte",
                    message);

        } catch (IllegalArgumentException e) {
            System.err.println("Erreur : " + e.getMessage());
        } catch (MessagingException e) {
            System.err.println("Erreur d'envoi d'email : " + e.getMessage());
        } catch (RestClientException e) {
            System.err.println("Erreur lors de la récupération des informations utilisateur : " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erreur inattendue : " + e.getMessage());
        }
    }

    @RabbitListener(queues = "rejectDemandeQueue")
    public void accountNotCreated(ClientAccountCreated event) throws IOException {

        Account sourceAccount;

        try {
            sourceAccount = util.getEmail(event.getNumeroClient());

            String message = "Echec de creation du Compte ! <br> Veuillez vous rapporcher de l'agence pour plus d'informations.<br><br> Cordialement, l'équipe support";

            mailservice.sendMail(sourceAccount.getEmail(), "Echec de creation de compte", message);
        }

        catch (MessagingException e) {
            System.out.println("Message non envoyé!");
        }
    }

    @RabbitListener(queues = "agentAccountCreateForMessageQueue")
    public void accountCreatedAgent(AgentEvent event) throws IOException {

        Account sourceAccount;

        try {
            sourceAccount = util.getEmailAgent(event.getMatricule());
            String message = "Compte Agent créé avec succès ! <br>Votre numero : " + sourceAccount.getTel()
                    + ",<br> Matricule : " + event.getMatricule()
                    + ". <br>Vous pouvez à présent profiter de nos services. <br><br> Cordialement, ";
            mailservice.sendMail(sourceAccount.getEmail(), "Confirmation de création de compte", message);

            System.out.println(message);

        }

        catch (MessagingException e) {
        }

    }

}
