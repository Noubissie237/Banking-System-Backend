package com.banking_system.service_users.events;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClientEvent {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("nom")
    private String nom;
    @JsonProperty("prenom")
    private String prenom;
    @JsonProperty("email")
    private String email;
    @JsonProperty("tel")
    private String tel;
    @JsonProperty("numero_cni")
    private String numero_cni;
    @JsonProperty("recto_cni")
    private String recto_cni;
    @JsonProperty("verso_cni")
    private String verso_cni;
    @JsonProperty("password")
    private String password;
}
