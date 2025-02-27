package com.banking_system.service_notification.events;

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
public class ClientAccountCreated {
    @JsonProperty("id_agence")
    private int idAgence;
    @JsonProperty("numero_client")
    private String numeroClient;

}
