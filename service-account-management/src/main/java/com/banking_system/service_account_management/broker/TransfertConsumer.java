package com.banking_system.service_account_management.broker;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.banking_system.service_account_management.event.TransfertEventConsumer;
import com.banking_system.service_account_management.services.AccountService;

@Component
public class TransfertConsumer {
   
    @Autowired
    private AccountService accountService;

    @RabbitListener(queues = "transfertMoneyQueue")
    public void receiveTransfertEvent(TransfertEventConsumer event) {
        accountService.makeTransfert(event);
    }
}
