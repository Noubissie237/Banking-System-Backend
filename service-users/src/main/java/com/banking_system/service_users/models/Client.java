package com.banking_system.service_users.models;

import jakarta.persistence.Entity;

@Entity
public class Client extends Person {

    public Client(String nom, String prenom, String email, String tel, String numero_cni, String recto_cni, String verso_cni, String password) {
        super(nom, prenom, email, tel, numero_cni, recto_cni, verso_cni, password);
    }
}
