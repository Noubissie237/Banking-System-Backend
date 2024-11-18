package com.banking_system.service_users.services;

import com.banking_system.service_users.events.AgentEventProducer;
import com.banking_system.service_users.models.Agent;
import com.banking_system.service_users.repositories.AgentRepository;
import com.banking_system.service_users.utils.Utils;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgentService {

    @Autowired
    AgentRepository agentRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    Utils utils;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void addAgent(Agent agent) {
        String matricule = utils.generateMatricule();
        agent.setMatricule(matricule);
        String encodedPassword = passwordEncoder.encode(agent.getPassword());
        agent.setPassword(encodedPassword);
        Agent savAgent = agentRepository.save(agent);
        String message = "Bienvenu M./Mme "+(agent.getPrenom().substring(0, 1).toUpperCase()+agent.getPrenom().substring(1).toLowerCase())+" "+agent.getNom().toUpperCase()+"\nMATRICULE : "+savAgent.getMatricule()+"\nVotre compte agent est en cours de création, cela peut prendre quelques instants.";
        System.out.println(message);
        AgentEventProducer event = new AgentEventProducer();
        event.setNumero(savAgent.getTel());
        event.setMatricule(savAgent.getMatricule());
        rabbitTemplate.convertAndSend("clientExchange", "agent.create", event);
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

    public Optional<Agent> getAgentByMatricule(String matricule) {
        return agentRepository.findByMatricule(matricule);
    }


}
