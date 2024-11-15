package com.banking_system.service_notification.broker;

import java.io.IOException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    @RabbitListener(queues = "clientAccountQueue")
    public void accountCreatedClient(ClientAccountCreated event) throws IOException {
        
        Account sourceAccount;
        
        try {
            sourceAccount = util.getEmail(event.getNumeroClient());
            String message = "Compte créé avec succès ! <br> Vous pouvez à présent profiter de nos services. <br><br> Cordialement,";
            mailservice.sendMail(sourceAccount.getEmail(),"Confirmation de création de compte", message);
        }
        catch (MessagingException e) {
            // Gère l'exception
        }

    }

    @RabbitListener(queues = "rejectDemandeQueue")
    public void accountNotCreated(ClientAccountCreated event) throws IOException {
        
        Account sourceAccount;
        
        try {
            sourceAccount = util.getEmail(event.getNumeroClient());
            
            String message = "Echec de creation du Compte ! <br> Veillez vous rapporcher de l'agence.<br><br> Cordialement,";
        
        mailservice.sendMail(sourceAccount.getEmail(),"Echec de creation de compte", message );
        }

        catch (MessagingException e) {
            // Gère l'exception
        }
    }

    @RabbitListener(queues = "agentAccountQueue")
    public void accountCreatedAgent(AgentEvent event) throws IOException {
        
        Account sourceAccount;
        
        try {
            sourceAccount = util.getEmailAgent(event.getMatricule());
            String message = "Compte Agent créé avec succès ! <br>Votre numero : " + sourceAccount.getTel() + ",<br> Matricule : " + event.getMatricule() + ". <br>Vous pouvez à présent profiter de nos services. <br><br> Cordialement, ";
            mailservice.sendMail(sourceAccount.getEmail(), "Confirmation de création de compte", message);
            System.out.println(message);

        }

        catch (MessagingException e) {
        }
        
    }



 
}
