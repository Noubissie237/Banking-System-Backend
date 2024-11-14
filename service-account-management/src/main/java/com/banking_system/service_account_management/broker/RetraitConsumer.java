package com.banking_system.service_account_management.broker;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.banking_system.service_account_management.event.RetraitEventConsumer;
import com.banking_system.service_account_management.services.AccountService;


@Component
public class RetraitConsumer {
    @Autowired
    private AccountService accountService;

    @RabbitListener(queues = "retraitMoneyQueue")
    public void receiveRetraitEvent(RetraitEventConsumer event) {
        accountService.makeRetrait(event);
    }
    
}
