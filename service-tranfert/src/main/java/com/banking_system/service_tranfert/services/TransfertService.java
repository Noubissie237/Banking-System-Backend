package com.banking_system.service_tranfert.services;

import java.io.IOException;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking_system.service_tranfert.events.Account;
import com.banking_system.service_tranfert.events.TransfertEventProducer;
import com.banking_system.service_tranfert.events.TransfertProducer;
import com.banking_system.service_tranfert.utils.Utils;

@Service
public class TransfertService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    Utils utils;

    public void transfertMoney(TransfertProducer event) throws IOException {

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

        Double frais = utils.getToDebit(event.getMontant(), sourceAccount.getAgenceId(), cibleAccount.getAgenceId());
        if (sourceAccount.getSolde() >= (event.getMontant() + frais)) {
            TransfertEventProducer transEvent = new TransfertEventProducer();
            transEvent.setAgence(event.getAgence());
            transEvent.setNumero_source(event.getNumero_source());
            transEvent.setNumero_cible(event.getNumero_cible());
            transEvent.setMontant(event.getMontant());
            transEvent.setFrais(frais);
            
            rabbitTemplate.convertAndSend("transactionExchange", "transfert.send", transEvent);
            rabbitTemplate.convertAndSend("transactionExchange", "transfert.send.agence", transEvent);
            System.out.println("Transfert effectué avec succès !");
        } else {
            System.out.println("Solde insuffisant !");
        }
    }
}
