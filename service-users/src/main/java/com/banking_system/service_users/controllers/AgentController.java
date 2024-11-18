package com.banking_system.service_users.controllers;

import com.banking_system.service_users.models.Agent;
import com.banking_system.service_users.services.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/get-user/{matricule}")
    public Optional<Agent> getAgentController(@PathVariable("matricule") String matricule){
        return agentService.getAgentByMatricule(matricule);
    }

}
