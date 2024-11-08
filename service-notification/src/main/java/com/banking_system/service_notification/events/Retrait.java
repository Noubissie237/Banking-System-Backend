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
public class Retrait {
    @JsonProperty("montant")
    private Double montant;
}
