package com.banking_system.service_authentification.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String phone;
    private String password;
}
