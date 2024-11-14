package com.banking_system.service_admin.broker;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.banking_system.service_admin.events.RetraitEventConsumer;
import com.banking_system.service_admin.services.AgenceService;

@Component
public class RetraitConsumer {
    @Autowired
    private AgenceService agenceService;

    @RabbitListener(queues = "retraitAgenceQueue")
    public void receiveRetraitEvent(RetraitEventConsumer event) {
        agenceService.incrementCapital(event.getAgence(), (event.getFrais() * 0.75));
    }
}
