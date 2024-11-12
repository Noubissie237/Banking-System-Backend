package com.banking_system.service_account_management.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AgentAccount extends Account {
    private String matricule;
    public AgentAccount(String number, int agenceId, double solde, LocalDateTime dateCreation, String matricule) {
        super(number, agenceId, solde, dateCreation);
        this.matricule = matricule;
    }

}
