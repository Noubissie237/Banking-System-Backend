package com.banking_system.service_users.services;

import com.banking_system.service_users.models.Agent;
import com.banking_system.service_users.repositories.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgentService {

    @Autowired
    AgentRepository agentRepository;

    public void addAgent(Agent agent) {
        agentRepository.save(agent);
    }

    public List<Agent> getAllAgents() {
        return agentRepository.findAll();
    }

    public Agent getAgentById(int id) {
        return agentRepository.findById(id).orElse(null);
    }

    public List<Agent> deleteAgent(int id) {
        agentRepository.deleteById(id);
        return agentRepository.findAll();
    }
}
