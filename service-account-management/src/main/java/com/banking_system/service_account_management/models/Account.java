package com.banking_system.service_account_management.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(AccountId.class)
public class Account {
    
    @Id
    private int agence_id;

    @Id
    private int user_number;

    private double solde = 0;

    private LocalDateTime date_creation = LocalDateTime.now(); 

}