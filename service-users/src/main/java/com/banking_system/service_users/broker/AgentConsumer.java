package com.banking_system.service_users.broker;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.banking_system.service_users.events.AgentEventConsumer;
import com.banking_system.service_users.models.Agent;
import com.banking_system.service_users.repositories.AgentRepository;
import com.banking_system.service_users.services.AgentService;

@Component
public class AgentConsumer {
    
    @Autowired
    AgentService agentService;

    @Autowired
    AgentRepository agentRepository;
    
    @RabbitListener(queues = "agentQueue")
    public void agentCreate(AgentEventConsumer event) {
        Agent agent = new Agent();
        agent.setNom(event.getNom());
        agent.setPrenom(event.getPrenom());
        agent.setEmail(event.getEmail());
        agent.setTel(event.getTel());
        agent.setNumero_cni(event.getNumero_cni());
        agent.setRecto_cni(event.getRecto_cni());
        agent.setVerso_cni(event.getVerso_cni());
        agent.setPassword(event.getPassword());
        agentService.addAgent(agent);
    }

}
