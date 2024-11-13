package com.banking_system.service_account_management.broker;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.banking_system.service_account_management.event.RechargeEventConsumer;
import com.banking_system.service_account_management.services.AccountService;

@Component
public class RechargeConsumer {
   
    @Autowired
    private AccountService accountService;

    @RabbitListener(queues = "rechargeByAgence")
    public void receiveRechargeEvent(RechargeEventConsumer event) {
        accountService.makeRecharge(event);
    }
}
