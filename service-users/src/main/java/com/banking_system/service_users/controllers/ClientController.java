package com.banking_system.service_users.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking_system.service_users.models.Client;
import com.banking_system.service_users.services.ClientService;




@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    ClientService clientService;

    @PostMapping("/add-client")
    public List<Client> addClientController(@RequestBody Client client) {
        clientService.addClient(client);
        return clientService.getAllClients();
    }

    @GetMapping("/delete-client/{id}")
    public List<Client> deleteClientController(@PathVariable("id") int id) {
        return clientService.deleteClient(id);
    }

    @GetMapping("/client/get/{number}")
    public Client getClient(@PathVariable String number) {
        return clientService.findClient(number);
    }

    @GetMapping("/get-clients")
    public List<Client> getMethodName() {
        return clientService.getAllClients();
    }
    
    @GetMapping("/get-clients-agence/{idAgence}")
    public List<Client> getClientByAgence(@PathVariable("idAgence") int idAgence) {
        return clientService.getClientByAgence(idAgence);
    }
    

}
