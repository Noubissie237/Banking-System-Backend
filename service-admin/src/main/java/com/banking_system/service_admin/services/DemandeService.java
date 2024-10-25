package com.banking_system.service_admin.services;

import com.banking_system.service_admin.models.Demande;
import com.banking_system.service_admin.models.StatutDemande;
import com.banking_system.service_admin.repositories.DemandeRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemandeService {

    @Autowired
    private DemandeRepository demandeRepository;

    public Demande createDemande(Demande demande) {
        return demandeRepository.save(demande);
    }

    public List<Demande> getAllDemandes() {
        return demandeRepository.findAll();
    }

    public Demande updateDemandeStatut(Long id, StatutDemande statut) {
        Optional<Demande> demandeOptional = demandeRepository.findById(id);
        
        if (demandeOptional.isPresent()) {
            Demande demande = demandeOptional.get();
            demande.setStatut(statut);
            return demandeRepository.save(demande);
        } else {
            throw new RuntimeException("Aucune demande avec l'id: " + id);
        }
    }

}
