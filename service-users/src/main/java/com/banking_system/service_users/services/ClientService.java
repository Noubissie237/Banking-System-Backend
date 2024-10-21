package com.banking_system.service_users.services;

import com.banking_system.service_users.models.Client;
import com.banking_system.service_users.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public void addClient(Client client) {
        clientRepository.save(client);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(int id) {
        return clientRepository.findById(id).orElse(null);
    }

    public List<Client> deleteClient(int id) {
        clientRepository.deleteById(id);
        return clientRepository.findAll();
    }
}
