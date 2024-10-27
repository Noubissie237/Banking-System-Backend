package com.banking_system.service_admin.services;

import com.banking_system.service_admin.events.ClientEventProducer;
import com.banking_system.service_admin.models.Demande;
import com.banking_system.service_admin.models.StatutDemande;
import com.banking_system.service_admin.repositories.DemandeRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemandeService {

    @Autowired
    private DemandeRepository demandeRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public Demande createDemande(Demande demande) {
        return demandeRepository.save(demande);
    }

    public List<Demande> getAllDemandes() {
        return demandeRepository.findAll();
    }

    public void deleteDemande(Long id) {
        demandeRepository.deleteById(id);
    }

    public List<Demande> updateDemandeStatut(Long id, StatutDemande statut) {
        Optional<Demande> demandeOptional = demandeRepository.findById(id);
        
        if (demandeOptional.isPresent()) {
            Demande demande = demandeOptional.get();
            demande.setStatut(statut);
            Demande demandeUpdated = demandeRepository.save(demande);
            if (statut == StatutDemande.ACCEPTEE)
            {                
                ClientEventProducer event = new ClientEventProducer();
                event.setIdAgence(1L);
                event.setNumeroClient(demandeUpdated.getClientTel());
                rabbitTemplate.convertAndSend("clientExchange", "demande.accepted", event);
                deleteDemande(demandeUpdated.getId());
            }
            else if (statut == StatutDemande.REJETEE) {
                String message = "Echec de création de votre compte 😣";
                rabbitTemplate.convertAndSend("clientExchange", "demande.reject", message);
            }
            return demandeRepository.findAll();
        } else {
            throw new RuntimeException("Aucune demande avec l'id: " + id);
        }
    }

}
