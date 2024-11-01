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

    public final static Double FRAIS_TRANSFERT_ORANGE_MONEY = 0.5;
    public final static Double FRAIS_RETRAIT_ORANGE_MONEY = 3.0;
    public final static Double FRAIS_TRANSFERT_MOBILE_MONEY = 1.0;
    public final static Double FRAIS_RETRAIT_MOBILE_MONEY = 2.5;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String login;
    private String password;
    private Double capital;


    public Agence(String nom, String login, String password, Double capital) {
        this.nom = nom;
        this.login = login;
        this.password = password;
        this.capital = capital;
    }
}
