package com.banking_system.service_users.events;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AgentEventProducer {
    @JsonProperty("numero")
    private String numero;
    @JsonProperty("matricule")
    private String matricule;
    
}
