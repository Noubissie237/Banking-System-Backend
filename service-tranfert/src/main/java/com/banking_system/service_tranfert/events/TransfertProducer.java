package com.banking_system.service_tranfert.events;

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
public class TransfertProducer {
    @JsonProperty("numero_source")
    private String numero_source;
    @JsonProperty("numero_cible")
    private String numero_cible;
    @JsonProperty("montant")
    private double montant;
    @JsonProperty("agence")
    private int agence;
}
