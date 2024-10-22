package com.banking_system.service_admin.services;

import com.banking_system.service_admin.models.Demande;
import com.banking_system.service_admin.repositories.DemandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemandeService {

    @Autowired
    private DemandeRepository demandeRepository;

    public Demande createDemande(Demande demande) {
        return demandeRepository.save(demande);
    }
}
