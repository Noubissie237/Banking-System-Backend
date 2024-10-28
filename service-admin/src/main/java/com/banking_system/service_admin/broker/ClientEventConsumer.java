package com.banking_system.service_admin.broker;

import com.banking_system.service_admin.events.ClientEventConsumerJson;
import com.banking_system.service_admin.models.Demande;
import com.banking_system.service_admin.models.StatutDemande;
import com.banking_system.service_admin.repositories.AdminRepository;

import java.time.LocalDateTime;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
public class ClientEventConsumer {

    @Autowired
    private AdminRepository adminRepo;

    @RabbitListener(queues = "clientQueue")
    public void receiveClientEvent(ClientEventConsumerJson event) {
        Demande demande = new Demande();
        demande.setTitre("Demande d'ouverture de compte");
        demande.setClientNom(event.getNom());
        demande.setClientPrenom(event.getPrenom());
        demande.setClientTel(event.getTel());
        demande.setClientNumeroCni(event.getNumero_cni());
        demande.setClientRectoCni(event.getRecto_cni());
        demande.setClientVersoCni(event.getVerso_cni());
        demande.setStatut(StatutDemande.EN_ATTENTE);
        demande.setDateCreation(LocalDateTime.now());
        adminRepo.save(demande);

    }
}
