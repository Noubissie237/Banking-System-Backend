package com.banking_system.service_users.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking_system.service_users.models.Agent;
import com.banking_system.service_users.services.AgentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AgentController {

    @Autowired
    AgentService agentService;

    @PostMapping("/add-agent")
    public List<Agent> addAgentController(@RequestBody Agent agent) {
        agentService.addAgent(agent);
        return agentService.getAllAgents();
    }

    @GetMapping("/delete-agent/{id}")
    public List<Agent> deleteAgentController(@PathVariable("id") int id) {
        return agentService.deleteAgent(id);
    }

    @GetMapping("/agent/get-num/{number}")
    public Agent getAgentnum(@PathVariable String number) {
        return agentService.findAgentnum(number);
    }

    @GetMapping("/agent/get/{number}")
    public Agent getAgent(@PathVariable String number) {
        return agentService.findAgent(number);
    }
    
    
    @GetMapping("/get-agent/{matricule}")
    public Optional<Agent> getAgentController(@PathVariable("matricule") String matricule){
        return agentService.getAgentByMatricule(matricule);
    }

}
