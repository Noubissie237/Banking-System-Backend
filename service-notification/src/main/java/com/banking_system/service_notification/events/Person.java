package com.banking_system.service_notification.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person {
    private String nom;
    private String prenom;
    private String email;
    private String tel;
    private String role; 
}
