package com.banking_system.service_users.models;

import jakarta.persistence.Column;
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
    private int id;
    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private String prenom;
    @Column(unique = true)
    private String email;
    // @Column(nullable = false, unique = true, length = 9)
    private String tel;
    @Column(nullable = false)
    private String numero_cni;
    @Column(nullable = false)
    private String recto_cni;
    @Column(nullable = false)
    private String verso_cni;
    @Column(nullable = false)
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
