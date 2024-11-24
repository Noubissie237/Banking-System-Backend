package com.banking_system.service_transactions.services;

import java.util.ArrayList;
import java.util.List;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.banking_system.service_transactions.dto.User;
import com.banking_system.service_transactions.models.TransactionEvent;
import com.eventstore.dbclient.EventData;
import com.eventstore.dbclient.EventStoreDBClient;
import com.eventstore.dbclient.ReadStreamOptions;
import com.eventstore.dbclient.ResolvedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Service
public class TransactionService {

    @Autowired
    private RestTemplate restTemplate;

    private final EventStoreDBClient eventStoreDBClient;
    private final ObjectMapper objectMapper;

    public TransactionService(EventStoreDBClient eventStoreDBClient) {
        this.eventStoreDBClient = eventStoreDBClient;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    public void createTransactionEvent(TransactionEvent event) {
        TransactionEvent transactionEvent = new TransactionEvent();
        transactionEvent.setNumeroSender(event.getNumeroSender());
        transactionEvent.setNumeroReceiver(event.getNumeroReceiver());
        transactionEvent.setAmount(event.getAmount());
        transactionEvent.setTransactionType(event.getTransactionType());
        transactionEvent.setAgenceId(event.getAgenceId());
        transactionEvent.setDateEvent(LocalDateTime.now());
        writeEvent("transactionsStream", transactionEvent.getTransactionType().toString(), transactionEvent);
    }

    private void writeEvent(String streamName, String type, TransactionEvent event) {
        try {
            String data = objectMapper.writeValueAsString(event);
            EventData eventData = EventData.builderAsJson(type.toString(), data).build();
            eventStoreDBClient.appendToStream(streamName, eventData).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<TransactionEvent> getTransactionByNumber(String numero) {

        String link = "http://localhost:8079/SERVICE-USERS/api/get-user/" + numero;

        User user = restTemplate.getForObject(link, User.class);

        String matricule = "";

        List<TransactionEvent> transactions = new ArrayList<>();
        try {
            List<ResolvedEvent> events = eventStoreDBClient.readStream("transactionsStream", ReadStreamOptions.get())
                    .get().getEvents();

            for (ResolvedEvent resolvedEvent : events) {
                String jsonData = new String(resolvedEvent.getEvent().getEventData(), StandardCharsets.UTF_8);

                if (jsonData.startsWith("\"") && jsonData.endsWith("\"")) {
                    jsonData = jsonData.substring(1, jsonData.length() - 1);
                }

                jsonData = jsonData.replace("\\\"", "\"");

                TransactionEvent transactionEvent = objectMapper.readValue(jsonData, TransactionEvent.class);

                if (transactionEvent.getNumeroSender().equals(numero)
                        || transactionEvent.getNumeroReceiver().equals(numero)) {
                    transactions.add(transactionEvent);
                }

                if (user != null) {
                    if (user.getRole().equals("AGENT")) {
                        matricule = user.getMatricule();
                        if (transactionEvent.getNumeroSender().equals(matricule)
                                || transactionEvent.getNumeroReceiver().equals(matricule)) {
                            transactions.add(transactionEvent);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactions;
    }

    public List<TransactionEvent> getAllTransactions() {
        List<TransactionEvent> transactions = new ArrayList<>();
        try {
            List<ResolvedEvent> events = eventStoreDBClient.readStream("transactionsStream", ReadStreamOptions.get())
                    .get().getEvents();

            for (ResolvedEvent resolvedEvent : events) {
                String jsonData = new String(resolvedEvent.getEvent().getEventData(), StandardCharsets.UTF_8);

                if (jsonData.startsWith("\"") && jsonData.endsWith("\"")) {
                    jsonData = jsonData.substring(1, jsonData.length() - 1);
                }

                jsonData = jsonData.replace("\\\"", "\"");

                TransactionEvent transactionEvent = objectMapper.readValue(jsonData, TransactionEvent.class);

                transactions.add(transactionEvent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactions;
    }

}
