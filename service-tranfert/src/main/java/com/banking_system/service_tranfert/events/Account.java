package com.banking_system.service_tranfert.events;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Account {
    private String number;
    private int agenceId;
    private double solde;
    private LocalDateTime dateCreation;
}
