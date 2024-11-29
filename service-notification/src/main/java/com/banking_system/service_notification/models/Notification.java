package com.banking_system.service_notification.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String titre;
    private String message;
    private int idAgence;
    private String destinataire;
    private LocalDateTime date;
}
