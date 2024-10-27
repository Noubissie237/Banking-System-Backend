package com.banking_system.service_account_management.broker;

import java.time.LocalDateTime;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking_system.service_account_management.event.AccountEventJson;
import com.banking_system.service_account_management.models.Account;
import com.banking_system.service_account_management.services.AccountService;

@Service
public class AccountConsumer {
    
    @Autowired
    private AccountService accountService;

    @RabbitListener(queues = "acceptDemandeQueue")
    public void receiveAccountEvent(AccountEventJson event) {
        Account account = new Account();
        account.setAgenceId(event.getIdAgence());
        account.setClientNumber(event.getNumeroClient());
        account.setDateCreation(LocalDateTime.now());
        account.setSolde(0);

        accountService.createAccount(account);
    }
}
