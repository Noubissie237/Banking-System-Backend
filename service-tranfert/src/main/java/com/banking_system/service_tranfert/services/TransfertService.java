package com.banking_system.service_tranfert.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking_system.service_tranfert.events.Account;
import com.banking_system.service_tranfert.events.TransfertProducer;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class TransfertService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void transfertMoney(TransfertProducer event) throws IOException
    {
        TransfertProducer transfert = new TransfertProducer();
        try {
            String urlString = "http://localhost:8079/SERVICE-ACCOUNT-MANAGEMENT/api/account/get/"+event.getNumero_source();
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                ObjectMapper objectMapper = new ObjectMapper();
                Account account = objectMapper.readValue(response.toString(), Account.class);
                
                if (account == null)
                    throw new IllegalArgumentException("Aucun compte avec le numéro :"+event.getNumero_source());

                transfert.setMontant(event.getMontant());
                transfert.setNumero_cible(event.getNumero_cible());
                transfert.setNumero_source(event.getNumero_source());
                rabbitTemplate.convertAndSend("transactionExchange", "start-transfert", transfert);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
}
