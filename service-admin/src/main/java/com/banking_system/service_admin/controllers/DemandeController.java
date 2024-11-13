package com.banking_system.service_admin.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banking_system.service_admin.events.RechargeEventProducer;
import com.banking_system.service_admin.models.Demande;
import com.banking_system.service_admin.models.StatutDemande;
import com.banking_system.service_admin.services.AdminService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/demande")
public class DemandeController {
    
    @Autowired
    AdminService adminService;

    @GetMapping("/get-all")
    public List<Demande> getDemandes() {
        return adminService.getAllDemandes();
    }

    @PutMapping("/update-statut/{id}")
    public List<Demande> updateDemandeStatut(@PathVariable int id, @RequestParam StatutDemande statut) {
        return adminService.updateDemandeStatut(id, statut);
    }

    @PostMapping("/recharge-account")
    public void rechargeAccount(@RequestBody RechargeEventProducer recharge) {
        adminService.chargeAccount(recharge.getAgence(), recharge.getNumero(), recharge.getMontant());;
    }
    
}
