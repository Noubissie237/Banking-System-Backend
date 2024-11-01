package com.banking_system.service_account_management.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking_system.service_account_management.models.Account;
import com.banking_system.service_account_management.services.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/account")
public class AccountController {
    
    @Autowired
    AccountService accountService;

    @GetMapping("/get/{number}")
    public Optional<Account> getAccountByNumber(@PathVariable String number) {
        return accountService.findAccountByNumber(number);
    }
    
}
