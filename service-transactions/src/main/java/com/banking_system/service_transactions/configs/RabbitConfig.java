package com.banking_system.service_transactions.configs;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;



@Configuration
@EnableRabbit
public class RabbitConfig {

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

    @Bean
    public TopicExchange transactionExchange(){
        return new TopicExchange("transactionExchange", true, false);
    }

    @Bean
    public Queue depotMoneyQueueForEvent() {
        return new Queue("depotMoneyQueueForEvent", true, false, false);
    }

    @Bean
    public Queue depotMoneyQueue1() {
        return new Queue("depotMoneyQueue1", true, false, false);
    }
    
    @Bean
    public Queue retraitMoneyQueueForTransactions() {
        return new Queue("retraitMoneyQueueForTransactions", true, false, false);
    }

    @Bean
    public Queue rechargeByAgence() {
        return new Queue("rechargeByAgence", true, false, false);
    }
}
