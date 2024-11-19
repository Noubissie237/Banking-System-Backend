package com.banking_system.service_notification.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String tel;
    private String numero_cni;
    private String recto_cni;
    private String verso_cni;
    private String password;
    private String matricule;
    private String role;
}