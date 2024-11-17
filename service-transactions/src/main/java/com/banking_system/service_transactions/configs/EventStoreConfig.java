package com.banking_system.service_transactions.configs;

import com.eventstore.dbclient.EventStoreDBClient;
import com.eventstore.dbclient.EventStoreDBClientSettings;
import com.eventstore.dbclient.EventStoreDBConnectionString;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventStoreConfig {

    @Bean
    public EventStoreDBClient eventStoreDBClient() {
        String connectionString = "esdb://localhost:2113?tls=false"; 
        EventStoreDBClientSettings settings = EventStoreDBConnectionString.parseOrThrow(connectionString);
        return EventStoreDBClient.create(settings);
    }
}
