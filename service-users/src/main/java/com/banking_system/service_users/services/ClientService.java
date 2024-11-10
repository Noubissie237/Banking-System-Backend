package com.banking_system.service_users.services;

import com.banking_system.service_authentification.dto.LoginRequest;
import com.banking_system.service_users.events.ClientEvent;
import com.banking_system.service_users.models.Client;
import com.banking_system.service_users.repositories.ClientRepository;
import com.banking_system.service_users.utils.Utils;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    Utils utils;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RestTemplate restTemplate;

    public void addClient(Client client) {
        try {
            String encodedPassword = passwordEncoder.encode(client.getPassword());
            String initialPassword = client.getPassword();
            client.setPassword(encodedPassword);
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
            String result = login(client.getTel(), initialPassword);
            System.out.println("Authentification Réussi !");
            System.out.println(result);
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

    public String login(String phone, String password) {
        String url = "http://localhost:8079/SERVICE-AUTHENTIFICATION/auth/login";
        LoginRequest loginRequest = new LoginRequest();

        loginRequest.setPhone(phone);
        loginRequest.setPassword(password);
        
        try {
            return restTemplate.postForObject(url, loginRequest, String.class);
        } catch (Exception e) {
            return "Echec !!!";
        }
    }

}
