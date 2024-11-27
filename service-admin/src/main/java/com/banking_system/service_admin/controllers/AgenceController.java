package com.banking_system.service_admin.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking_system.service_admin.models.Agence;
import com.banking_system.service_admin.services.AgenceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/agence")
public class AgenceController {
    
    @Autowired
    private AgenceService agenceService;

    @PostMapping("/create-agence")
    public Agence createAgence(@RequestBody Agence agence) {
        return agenceService.createAgence(agence);
    }


    @GetMapping("/get-agences")
    public List<Agence> getAgences() {
        return agenceService.getAllAgences();
    }
    
    @GetMapping("/get-solde-agence/{id}")
    public double getSoldeAgence(@PathVariable int id) {
        return agenceService.getSolde(id);
    }

}
