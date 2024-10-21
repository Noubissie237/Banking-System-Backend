package com.banking_system.service_users.models;

import jakarta.persistence.Entity;

@Entity
public class Agent extends Person {
    private String matricule;

    public Agent(String nom, String prenom, String email, String tel, String numero_cni, String recto_cni, String verso_cni, String password, String matricule) {
        super(nom, prenom, email, tel, numero_cni, recto_cni, verso_cni, password);
        this.matricule = matricule;
    }

    public Agent() {}

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }
}
