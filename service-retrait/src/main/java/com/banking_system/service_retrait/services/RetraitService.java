package com.banking_system.service_retrait.services;

import java.io.IOException;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking_system.service_retrait.events.Account;
import com.banking_system.service_retrait.events.RetraitEventProducer;
import com.banking_system.service_retrait.events.RetraitProducer;
import com.banking_system.service_retrait.utils.Utils;

@Service
public class RetraitService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    Utils utils;

    public void retirerMoney(RetraitProducer event) throws IOException {

        event.setAgence(utils.getAgenceId(event.getNumero_cible()));

        Account Accountcible;

        try {
            Accountcible = utils.getAccount(event.getNumero_cible());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Aucun compte enregistré avec ce numéro " + event.getNumero_cible());
        }

        Double frais = utils.getToRemove(event.getMontant(), Accountcible.getAgenceId());

        if (Accountcible.getSolde() >= (event.getMontant() + frais)) {
            RetraitEventProducer retraitEvent = new RetraitEventProducer();
            retraitEvent.setNumero_cible(event.getNumero_cible());
            retraitEvent.setMatricule_agent(event.getMatricule_agent());
            retraitEvent.setMontant(event.getMontant());
            retraitEvent.setAgence(event.getAgence());
            retraitEvent.setFrais(frais);

            rabbitTemplate.convertAndSend("transactionExchange", "retrait.send", retraitEvent);

        } else {
            throw new RuntimeException("Solde insuffisant !");
        }
    }
}
