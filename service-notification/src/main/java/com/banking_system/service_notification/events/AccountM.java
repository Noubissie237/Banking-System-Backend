package com.banking_system.service_notification.events;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class AccountM {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String number;
    private int agenceId;
    private double solde = 0;
    private LocalDateTime dateCreation;

    public AccountM(String number, int agenceId, double solde, LocalDateTime dateCreation) {
        this.number = number;
        this.agenceId = agenceId;
        this.solde = solde;
        this.dateCreation = dateCreation;
    }

}