package com.banking_system.service_admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking_system.service_admin.models.Agence;
import com.banking_system.service_admin.services.AgenceService;

@RestController
@RequestMapping("/api/agence")
public class AgenceController {
    
    @Autowired
    private AgenceService agenceService;

    @PostMapping("/create-agence")
    public Agence createAgence(@RequestBody Agence agence) {
        return agenceService.createAgence(agence);
    }
}
