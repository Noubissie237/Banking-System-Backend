package com.banking_system.service_account_management.broker;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.banking_system.service_account_management.event.DepotEventConsumer;
import com.banking_system.service_account_management.services.AccountService;

@Component
public class DepotConsumer {
   
    @Autowired
    private AccountService accountService;

    @RabbitListener(queues = "depotMoneyQueue")
    public void receiveDepotEvent(DepotEventConsumer event) {
        accountService.makeDepot(event);
    }
}
