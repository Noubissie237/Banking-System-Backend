package com.banking_system.service_transactions.events;

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
public class RechargeTemplate {
    @JsonProperty("agence")
    private int agence;
    @JsonProperty("numero")
    private String numero;
    @JsonProperty("montant")
    private double montant;
}
