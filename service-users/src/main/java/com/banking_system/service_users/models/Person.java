package com.banking_system.service_users.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Person {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
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

    public Person(){}

    public int getId(){ return id; }
    public void setId(int id) { this.id = id; }
    public String getNom(){ return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom(){ return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getEmail(){ return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTel(){ return tel; }
    public void setTel(String tel) { this.tel = tel; }
    public String getNumero_cni(){ return numero_cni; }
    public void setNumero_cni(String numero_cni) { this.numero_cni = numero_cni; }
    public String getRecto_cni(){ return recto_cni; }
    public void setRecto_cni(String recto_cni) { this.recto_cni = recto_cni; }
    public String getVerso_cni(){ return verso_cni; }
    public void setVerso_cni(String verso_cni) { this.verso_cni = verso_cni; }
    public String getPassword(){ return password; }
    public void setPassword(String password) { this.password = password; }
    
}
