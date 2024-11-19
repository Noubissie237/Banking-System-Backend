package com.banking_system.service_notification.events;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccountEventJson {
    @JsonProperty("id_agence")
    private int idAgence;
    @JsonProperty("numero_client")
    private String numeroClient;


}
