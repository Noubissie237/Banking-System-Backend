package com.banking_system.service_users.controllers;

import com.banking_system.service_users.models.Client;
import com.banking_system.service_users.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173" , allowedHeaders = "*")
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

    

}
