package com.banking_system.service_transactions.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionEvent {
    
    @JsonProperty("transactionType")
    private TransactionType transactionType;
    
    @JsonProperty("numeroSender")
    private String numeroSender;

    @JsonProperty("agenceId")
    private int agenceId;
    
    @JsonProperty("numeroReceiver")
    private String numeroReceiver;
    
    @JsonProperty("amount")
    private double amount;

    @JsonProperty("dateEvent")
    private LocalDateTime dateEvent;
}