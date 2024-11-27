package com.banking_system.service_notification.events;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RetraitProducer {
    @JsonProperty("numero_cible")
    private String numero_cible;
    @JsonProperty("matricule_agent")
    private String matricule_agent;
    @JsonProperty("montant")
    private double montant;
    @JsonProperty("agence")
    private int agence;
    @JsonProperty("pass")
    private String pass;
}
