package com.banking_system.service_depot.services;

import java.io.IOException;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking_system.service_depot.events.Account;
import com.banking_system.service_depot.events.DepotEventProducer;
import com.banking_system.service_depot.events.DepotProducer;
import com.banking_system.service_depot.utils.Utils;

@Service
public class DepotService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    Utils utils;

    public void depotMoney(DepotProducer event) throws IOException {

        event.setAgence(utils.getAgenceId(event.getNumero_source()));

        Account sourceAccount;
        try {
            sourceAccount = utils.getAccount(event.getNumero_source());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Le compte source avec le numéro " + event.getNumero_source() + " n'existe pas.");
        }

        Account cibleAccount;
        try {
           cibleAccount = utils.getAccount(event.getNumero_cible());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Le compte cible avec le numéro " + event.getNumero_cible() + " n'existe pas.");
        }

        double frais = utils.getToDebit(event.getMontant(), sourceAccount.getAgenceId(), cibleAccount.getAgenceId());
        if (sourceAccount.getSolde() >= (event.getMontant() + frais)) {
            DepotEventProducer transEvent = new DepotEventProducer();
            transEvent.setAgence(event.getAgence());
            transEvent.setNumero_source(event.getNumero_source());
            transEvent.setNumero_cible(event.getNumero_cible());
            transEvent.setMontant(event.getMontant());
            transEvent.setFrais(frais);
            
            rabbitTemplate.convertAndSend("transactionExchange", "depot.send", transEvent);

        } else {
            throw new RuntimeException("Solde insuffisant !");
        }
    }
}
