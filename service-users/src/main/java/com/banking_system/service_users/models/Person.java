package com.banking_system.service_users.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String tel;
    private String numero_cni;
    private String recto_cni;
    private String verso_cni;
    private String password;

    public Person(String nom, String prenom, String email, String tel, String numero_cni, String recto_cni, String verso_cni, String password){
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.tel = tel;
        this.numero_cni = numero_cni;
        this.recto_cni = recto_cni;
        this.verso_cni = verso_cni;
        this.password = password;
    }
    
}
