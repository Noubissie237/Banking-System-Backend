package com.banking_system.service_admin.services;

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
}
