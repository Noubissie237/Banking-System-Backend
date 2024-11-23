package com.banking_system.service_authentification.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Person {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String tel;
    private String numero_cni;
    private String recto_cni;
    private String verso_cni;
    private String password;
    private String role;
}
