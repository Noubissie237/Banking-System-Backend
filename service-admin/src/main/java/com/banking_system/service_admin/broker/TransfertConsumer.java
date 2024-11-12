package com.banking_system.service_admin.broker;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.banking_system.service_admin.events.TransfertEventConsumer;
import com.banking_system.service_admin.services.AgenceService;

@Component
public class TransfertConsumer {
    
    @Autowired
    private AgenceService agenceService;

    @RabbitListener(queues = "transfertMoneyQueueAgence")
    public void receiveEventTransfert(TransfertEventConsumer event) {
        agenceService.inscrementCapital(event.getAgence(), event.getFrais());
    }
}
