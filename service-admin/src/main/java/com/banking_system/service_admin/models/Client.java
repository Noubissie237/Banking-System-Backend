package com.banking_system.service_admin.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
public class Client {

    @Id
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String tel;
    private String numero_cni;
    private String recto_cni;
    private String verso_cni;
    private String password;


}
