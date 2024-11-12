package com.banking_system.service_notification.broker;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.banking_system.service_notification.events.AgentEvent;
import com.banking_system.service_notification.events.ClientAccountCreated;
import com.banking_system.service_notification.services.EmailSender;

@Component
public class MessageCompteCreate {
   
    @Autowired
    private EmailSender mailservice;

    @RabbitListener(queues = "clientAccountQueue")
    public void accountCreatedClient(ClientAccountCreated event) {
        
        mailservice.sendMail("sorelletamen8@gmail.com","Confirmation de création de compte", event.getMessage());
        System.out.println(event.getMessage());
    }

    @RabbitListener(queues = "rejectDemandeQueue")
    public void accountNotCreated(ClientAccountCreated event) {
        mailservice.sendMail("sorelletamen8@gmail.com","Echec de creation de compte", event.getMessage());
        
    }

    @RabbitListener(queues = "agentAccountQueue")
    public void accountCreatedAgent(AgentEvent event) {
        String message = "Compte Agent créé avec succès ! Votre numero est " + event.getNumero() + " Matricule " + event.getMatricule() + " Vous pouvez à présent profiter de nos services 😀 ";
        mailservice.sendMail("sorelletamen8@gmail.com", "Confirmation de création de compte", message);
        System.out.println(message);
        
    }

 
}
