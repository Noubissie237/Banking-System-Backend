package com.banking_system.service_admin.broker;

import com.banking_system.service_admin.events.ClientEvent;
import com.banking_system.service_admin.models.Demande;
import com.banking_system.service_admin.models.StatutDemande;
import com.banking_system.service_admin.services.DemandeService;

import java.time.LocalDateTime;

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
        demande.setClientNom(event.getNom());
        demande.setClientPrenom(event.getPrenom());
        demande.setClientTel(event.getTel());
        demande.setClientNumeroCni(event.getNumero_cni());
        demande.setClientRectoCni(event.getRecto_cni());
        demande.setClientVersoCni(event.getVerso_cni());
        demande.setStatut(StatutDemande.EN_ATTENTE);
        demande.setDateCreation(LocalDateTime.now());

        demandeService.createDemande(demande);
    }
}
