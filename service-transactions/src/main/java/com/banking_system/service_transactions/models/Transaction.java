package com.banking_system.service_transactions.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private TypeTransaction type_transaction;
    private LocalDateTime date_creation;
    @Enumerated(EnumType.STRING)
    private StatutTransaction statut;
    private String transactionID;
    private Double montant;
    private String numero_client;
    private String matricule_agent;
    private String numero_expediteur;
    private String numero_destinataire;
    private String compte_expediteur;
    private String compte_destinataire;
    private Double solde_avant_transaction;
    private Double solde_apres_transaction;
    private Double frais;
    private String commentaire;
    

}
