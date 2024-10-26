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
    private Long id;

    private String clientNumber;
    private Long agenceId;
    private double solde = 0;
    private LocalDateTime dateCreation;

    public Account(String clientNumber, Long agenceId, double solde, LocalDateTime dateCreation) {
        this.clientNumber = clientNumber;
        this.agenceId = agenceId;
        this.solde = solde;
        this.dateCreation = dateCreation;
    }

}