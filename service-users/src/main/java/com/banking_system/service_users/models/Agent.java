package com.banking_system.service_users.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Agent extends Person {


    @Column(unique = true)
    private String matricule;

    private Role role = Role.AGENT;

    public Agent(String nom, String prenom, String email, String tel, String numero_cni, String recto_cni, String verso_cni, String password, String matricule) {
        super(nom, prenom, email, tel, numero_cni, recto_cni, verso_cni, password);
        this.matricule = matricule;
    }
}
