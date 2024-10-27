package com.banking_system.service_users.broker;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.banking_system.service_users.events.ClientAccountCreated;

@Component
public class ClientConsumer {
    
    @RabbitListener(queues = "accountQueue")
    public void accountCreated(ClientAccountCreated event) {
        System.out.println(event.getMessage());
    }

    @RabbitListener(queues = "rejectDemandeQueue")
    public void accountNotCreated(ClientAccountCreated event) {
        System.out.println(event.getMessage());
    }
}
