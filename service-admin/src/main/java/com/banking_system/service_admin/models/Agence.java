package com.banking_system.service_admin.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Agence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String login;
    private String password;
    private double capital;


    public Agence(String nom, String login, String password, double capital) {
        this.nom = nom;
        this.login = login;
        this.password = password;
        this.capital = capital;
    }
}
