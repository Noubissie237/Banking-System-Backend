package com.banking_system.service_admin.events;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientEventConsumerJson {
    @JsonProperty("id")
    private int id;
    @JsonProperty("nom")
    private String nom;
    @JsonProperty("prenom")
    private String prenom;
    @JsonProperty("tel")
    private String tel;
    @JsonProperty("numero_cni")
    private String numero_cni;
    @JsonProperty("recto_cni")
    private String recto_cni;
    @JsonProperty("verso_cni")
    private String verso_cni;
    @JsonProperty("agence")
    private int agence;
}
