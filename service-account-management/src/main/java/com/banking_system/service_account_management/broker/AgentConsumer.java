package com.banking_system.service_account_management.broker;

import java.time.LocalDateTime;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.banking_system.service_account_management.event.AgentEventConsumer;
import com.banking_system.service_account_management.models.AgentAccount;
import com.banking_system.service_account_management.services.AccountService;
import com.banking_system.service_account_management.utils.Util;

@Component
public class AgentConsumer {
    
    @Autowired
    private AccountService accountService;

    @Autowired
    private Util util;

    @RabbitListener(queues = "agentCreateQueue")
    public void receiveAgentEvent(AgentEventConsumer event) {
        AgentAccount account = new AgentAccount();
        int agenceId = util.isAnMtnNumber(event.getNumero()) ? 1 : 2;
        account.setAgenceId(agenceId);
        account.setDateCreation(LocalDateTime.now());
        account.setNumber(event.getNumero());
        account.setSolde(0.0);
        account.setMatricule(event.getMatricule());
        accountService.createAgentAccount(account);
    }
}
