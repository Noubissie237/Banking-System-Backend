package com.banking_system.service_authentification.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Person {
    private String name;
    private String email;
    private String tel;
    private String password;
    private String role;
    private double solde;
}
