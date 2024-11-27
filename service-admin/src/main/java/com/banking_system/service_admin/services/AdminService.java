package com.banking_system.service_admin.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking_system.service_admin.events.AgentEventProducer;
import com.banking_system.service_admin.events.ClientEventProducer;
import com.banking_system.service_admin.events.RechargeEventProducer;
import com.banking_system.service_admin.models.Demande;
import com.banking_system.service_admin.models.StatutDemande;
import com.banking_system.service_admin.repositories.AdminRepository;
import com.banking_system.service_admin.utils.Util;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    AgenceService agenceService;

    @Autowired
    Util util;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public Demande createDemande(Demande demande) {
        return adminRepository.save(demande);
    }

    public List<Demande> getAllDemandes() {
        return adminRepository.findAll();
    }

    public List<Demande> getAgenceDemande(int idAgence) {
        List<Demande> demandes = adminRepository.findAll();
        List<Demande> result = new ArrayList<>(demandes);

        for(Demande demande : demandes) {
            if ((idAgence == 1 && util.isAnOrangeNumber(demande.getClientTel())) ||
                (idAgence != 1 && util.isAnMtnNumber(demande.getClientTel()))) {
                    result.remove(demande);
                }
        }
        return result;
    }

    public void deleteDemande(int id) {
        adminRepository.deleteById(id);
    }

    public List<Demande> updateDemandeStatut(int id, StatutDemande statut) {
        Optional<Demande> demandeOptional = adminRepository.findById(id);
        
        if (demandeOptional.isPresent()) {
            Demande demande = demandeOptional.get();
            demande.setStatut(statut);
            Demande demandeUpdated = adminRepository.save(demande);
            if (statut == StatutDemande.ACCEPTEE)
            {                
                ClientEventProducer event = new ClientEventProducer();
                event.setIdAgence(demandeUpdated.getAgence());
                event.setNumeroClient(demandeUpdated.getClientTel());
            
                rabbitTemplate.convertAndSend("clientExchange", "demande.accepted", event);
                deleteDemande(demandeUpdated.getId());
            }
            else if (statut == StatutDemande.REJETEE) {
                ClientEventProducer event = new ClientEventProducer();
                event.setIdAgence(demandeUpdated.getAgence());
                event.setNumeroClient(demandeUpdated.getClientTel());
                rabbitTemplate.convertAndSend("clientExchange", "demande.reject", event);
            }
            return adminRepository.findAll();
        } else {
            throw new RuntimeException("Aucune demande avec l'id: " + id);
        }
    }

    public AgentEventProducer createAgent(AgentEventProducer agent) {
        AgentEventProducer eventAgent = new AgentEventProducer();
        eventAgent.setNom(agent.getNom());
        eventAgent.setPrenom(agent.getPrenom());
        eventAgent.setEmail(agent.getEmail());
        eventAgent.setTel(agent.getTel());
        eventAgent.setNumero_cni(agent.getNumero_cni());
        eventAgent.setRecto_cni(agent.getRecto_cni());
        eventAgent.setVerso_cni(agent.getVerso_cni());
        eventAgent.setPassword(eventAgent.getTel());
        rabbitTemplate.convertAndSend("clientExchange", "agent.demande");
        return eventAgent;
    }

    public void chargeAccount(int idAgence, String number, double montant) {
        RechargeEventProducer event = new RechargeEventProducer();
        event.setAgence(idAgence);
        event.setNumero(number);
        event.setMontant(montant);

        rabbitTemplate.convertAndSend("transactionExchange", "recharge.send", event);
        rabbitTemplate.convertAndSend("transactionExchange", "recharge.sen", event);
        agenceService.decrementCapital(idAgence, montant);
    }

}
