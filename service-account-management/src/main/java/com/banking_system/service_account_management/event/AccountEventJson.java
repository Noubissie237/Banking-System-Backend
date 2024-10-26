package com.banking_system.service_account_management.event;

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
    private Long idAgence;
    @JsonProperty("numero_client")
    private String numeroClient;

}
