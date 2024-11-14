package com.banking_system.service_admin.events;

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
public class RechargeEventProducer {
    @JsonProperty("agence")
    private int agence;
    @JsonProperty("numero")
    private String numero;
    @JsonProperty("montant")
    private Double montant;
}
