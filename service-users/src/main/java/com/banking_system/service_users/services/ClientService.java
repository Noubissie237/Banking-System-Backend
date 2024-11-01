package com.banking_system.service_users.services;

<<<<<<< HEAD
import java.util.List;
=======
import com.banking_system.service_users.events.ClientEvent;
import com.banking_system.service_users.models.Client;
import com.banking_system.service_users.repositories.ClientRepository;
import com.banking_system.service_users.utils.Utils;
>>>>>>> a0f6b6ed3a7835813e2275331a65fd0d6a084c07

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking_system.service_users.events.ClientEvent;
import com.banking_system.service_users.models.Client;
import com.banking_system.service_users.repositories.ClientRepository;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    Utils utils;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void addClient(Client client) {
        try {
            ClientEvent event = new ClientEvent();
            event.setAgence(utils.getAgenceId(client.getTel()));
            String message = "Bienvenu M./Mme "+(client.getPrenom().substring(0, 1).toUpperCase()+client.getPrenom().substring(1).toLowerCase())+" "+client.getNom().toUpperCase()+" \nMerci de vous être enregistré. Votre compte est en cours de création. Cela peut prendre quelques instants.";
            System.out.println(message);
            event.setNom(client.getNom());
            event.setPrenom(client.getPrenom());
            event.setTel(client.getTel());
            event.setNumero_cni(client.getNumero_cni());
            event.setRecto_cni(client.getRecto_cni());
            event.setVerso_cni(client.getVerso_cni());
            clientRepository.save(client);
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
