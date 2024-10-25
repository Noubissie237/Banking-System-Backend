package com.banking_system.service_users.services;

import com.banking_system.service_users.events.ClientEvent;
import com.banking_system.service_users.models.Client;
import com.banking_system.service_users.repositories.ClientRepository;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void addClient(Client client) {
        try {
            Client saveClient = clientRepository.save(client);
            ClientEvent event = new ClientEvent();
            event.setId(saveClient.getId());
            event.setNom(saveClient.getNom());
            event.setPrenom(saveClient.getPrenom());
            event.setEmail(saveClient.getEmail());
            event.setTel(saveClient.getTel());
            event.setNumero_cni(saveClient.getNumero_cni());
            event.setRecto_cni(saveClient.getRecto_cni());
            event.setVerso_cni(saveClient.getVerso_cni());
            event.setPassword(saveClient.getPassword());
            rabbitTemplate.convertAndSend("clientExchange", "client.create", event);
        } catch (Exception e) {
            throw new RuntimeException("Client Insertion Error : ", e);
        }
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
