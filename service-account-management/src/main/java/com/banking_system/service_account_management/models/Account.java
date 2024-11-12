package com.banking_system.service_account_management.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Account {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String number;
    private int agenceId;
    private double solde = 0;
    private LocalDateTime dateCreation;

    public Account(String number, int agenceId, double solde, LocalDateTime dateCreation) {
        this.number = number;
        this.agenceId = agenceId;
        this.solde = solde;
        this.dateCreation = dateCreation;
    }

}