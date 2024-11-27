package com.banking_system.service_authentification.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Agence {
    private int id;
    private String nom;
    private String login;
    private String password;
    private double capital;
}
