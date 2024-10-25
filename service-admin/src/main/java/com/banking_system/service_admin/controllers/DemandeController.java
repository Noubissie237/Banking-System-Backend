package com.banking_system.service_admin.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banking_system.service_admin.models.Demande;
import com.banking_system.service_admin.models.StatutDemande;
import com.banking_system.service_admin.services.DemandeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/demande")
public class DemandeController {
    
    @Autowired
    DemandeService demandeService;

    @GetMapping("/get-all")
    public List<Demande> getDemandes() {
        return demandeService.getAllDemandes();
    }

    @PutMapping("/update-statut/{id}")
    public Demande updateDemandeStatut(@PathVariable Long id, @RequestParam StatutDemande statut) {
        return demandeService.updateDemandeStatut(id, statut);
    }
    
}
