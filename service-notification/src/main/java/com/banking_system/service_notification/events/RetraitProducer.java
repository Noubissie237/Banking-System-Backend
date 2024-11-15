package com.banking_system.service_notification.events;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Data
@ToString
@NoArgsConstructor
@Getter
@Setter
public class RetraitProducer {
    @JsonProperty("numero_cible")
    private String numero_cible;
    @JsonProperty("numero_agent")
    private String numero_agent;
    @JsonProperty("montant")
    private Double montant;
    @JsonProperty("agence")
    private int agence;
}