package com.banking_system.service_retrait.events;

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
public class RetraitEventProducer {
    @JsonProperty("numero_cible")
    private String numero_cible;

    @JsonProperty("numero_agent")
    private String numero_agent;

    @JsonProperty("montant")
    private Double montant;

    @JsonProperty("agence")
    private int agence;

    @JsonProperty("frais")
    private Double frais;

    
}
