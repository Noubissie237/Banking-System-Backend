package com.banking_system.service_admin.broker;

import com.banking_system.service_admin.events.ClientEvent;
import com.banking_system.service_admin.models.Demande;
import com.banking_system.service_admin.services.DemandeService;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// import java.time.LocalDateTime;

@Component
public class ClientEventConsumer {

    @Autowired
    private DemandeService demandeService;

    @RabbitListener(queues = "clientQueue")
    public void receiveClientEvent(ClientEvent event) {
        Demande demande = new Demande();
        demande.setTitre("Demande d'ouverture de compte");
        demande.setClientId(event.getId());
        demande.setStatut(null);
        demande.setDateCreation(null);

        demandeService.createDemande(demande);
    }
}
