package com.banking_system.service_account_management.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking_system.service_account_management.models.Account;
import com.banking_system.service_account_management.models.AgentAccount;
import com.banking_system.service_account_management.services.AccountService;


@RestController
@RequestMapping("/api/account")
public class AccountController {
    
    @Autowired
    AccountService accountService;


    @GetMapping("/get/{number}")
    public Account getAccountByNumber(@PathVariable String number) {
        return accountService.findAccountByNumber(number);
    }

    @GetMapping("/get-client-accounts/{id}")
    public List<Account> getClientAccount(@PathVariable int id) {
        return accountService.findAllAccount(id);
    }

    @GetMapping("/get-agent-accounts/{id}")
    public List<AgentAccount> getAgentAccount(@PathVariable int id) {
        return accountService.findAllAgentAccount(id);
    }
    

}
