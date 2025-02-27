package com.banking_system.service_admin.models;
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
public class Demande {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titre;
    private LocalDateTime dateCreation;
    private String clientNom;
    private String clientPrenom;
    private String clientTel;
    private String clientNumeroCni;
    private String clientRectoCni;
    private String clientVersoCni;
    private int agence;
    @Enumerated(EnumType.STRING)
    private StatutDemande statut;

    public Demande(String titre, String clientNom, String clientPrenom, String clientTel, String clientNumeroCni, String clientRectoCni, String clientVersoCni, StatutDemande statut, LocalDateTime date) {
        this.titre = titre;
        this.statut = statut;
        this.dateCreation = date;
        this.clientNom = clientNom;
        this.clientPrenom = clientPrenom;
        this.clientTel = clientTel;
        this.clientNumeroCni = clientNumeroCni;
        this.clientRectoCni = clientRectoCni;
        this.clientVersoCni = clientVersoCni;
    }


}
