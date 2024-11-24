package com.banking_system.service_admin.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking_system.service_admin.models.Agence;
import com.banking_system.service_admin.repositories.AgenceRepository;

@Service
public class AgenceService {
    
    @Autowired
    AgenceRepository agenceRepository;

    public Agence createAgence(Agence agence) {
        return agenceRepository.save(agence);
    }

    public void incrementCapital(int idAgence, Double montant) {
        Agence agence;
        agence = agenceRepository.findById(idAgence).orElseThrow(() -> new IllegalArgumentException("Agence non trouvée !"));
        agence.setCapital((agence.getCapital() + montant));
        agenceRepository.save(agence);
    }

    public void decrementCapital(int idAgence, Double montant) {
        Agence agence;
        agence = agenceRepository.findById(idAgence).orElseThrow(() -> new IllegalArgumentException("Agence non trouvée !"));
        agence.setCapital((agence.getCapital() - montant));
        agenceRepository.save(agence);
    }

    public List<Agence> getAllAgences() {
        return agenceRepository.findAll();
    }


}
