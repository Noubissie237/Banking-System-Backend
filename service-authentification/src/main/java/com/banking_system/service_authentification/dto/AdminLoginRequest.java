package com.banking_system.service_authentification.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminLoginRequest {
    private String login;
    private String password;
}
