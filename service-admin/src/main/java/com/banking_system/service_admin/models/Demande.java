package com.banking_system.service_admin.models;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// import java.util.Date;

@Entity
@Getter
@Setter
public class Demande {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    // @ManyToOne
    // @JoinColumn(name = "id", nullable = false)
    // private Client client;
    // @Temporal(TemporalType.TIMESTAMP)
    // private Date dateCreation;
    private LocalDateTime dateCreation;
    private Long clientId;
    @Enumerated(EnumType.STRING)
    private StatutDemande statut;

    public Demande() {}

    public Demande(String titre, Long clientId, StatutDemande statut, LocalDateTime date) {
        this.titre = titre;
        this.clientId = clientId;
        this.statut = statut != null ? statut : StatutDemande.EN_ATTENTE;
        this.dateCreation = date != null ? date : LocalDateTime.now(); ;
    }


}
