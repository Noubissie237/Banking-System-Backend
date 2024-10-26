package com.banking_system.service_admin.broker;

import com.banking_system.service_admin.events.ClientEventConsumerJson;
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
    public void receiveClientEvent(ClientEventConsumerJson event) {
        System.out.println("############1");
        Demande demande = new Demande();
        demande.setTitre("Demande d'ouverture de compte");
        System.out.println("############2");
        demande.setClientId(event.getId());
        System.out.println("############3");
        demande.setClientNom(event.getNom());
        System.out.println("############4");
        demande.setClientPrenom(event.getPrenom());
        System.out.println("############5");
        demande.setClientTel(event.getTel());
        System.out.println("############6");
        demande.setClientNumeroCni(event.getNumero_cni());
        System.out.println("############7");
        demande.setClientRectoCni(event.getRecto_cni());
        System.out.println("############8");
        demande.setClientVersoCni(event.getVerso_cni());
        System.out.println("############9");
        demande.setStatut(StatutDemande.EN_ATTENTE);
        System.out.println("############10");
        demande.setDateCreation(LocalDateTime.now());
        System.out.println("############11");
        demandeService.createDemande(demande);
        System.out.println("############12");

    }
}
