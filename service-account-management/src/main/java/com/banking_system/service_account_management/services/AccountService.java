package com.banking_system.service_account_management.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking_system.service_account_management.models.Account;
import com.banking_system.service_account_management.repositories.AccountRepository;

@Service
public class AccountService {
    
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void createAccount(Account account) {
        try {
            accountRepository.save(account);
            String message = "Compte créé avec succès ! Vous pouvez à présent profiter de nos services 😀 ";
            rabbitTemplate.convertAndSend("clientExchange", "account.create", message);
        } catch (Exception e) {
            throw new RuntimeException("Account Creation Error : ",e);
        }
    }
}
