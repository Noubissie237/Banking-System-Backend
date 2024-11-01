package com.banking_system.service_account_management.config;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

import org.springframework.amqp.core.Binding;


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
    public TopicExchange clientExchange() {
        return new TopicExchange("clientExchange");
    }

    @Bean
    public TopicExchange transactionExchange(){
        return new TopicExchange("transactionExchange", true, false);
    }

    @Bean
    public Queue acceptDemandeQueue() {
        return new Queue("acceptDemandeQueue");
    }

    @Bean
    public Queue clientAccountQueue() {
        return new Queue("clientAccountQueue", true, false, false);
    }

    @Bean
    public Queue agentAccountQueue() {
        return new Queue("agentAccountQueue", true, false, false);
    }

    @Bean
    public Queue agentCreateQueue() {
        return new Queue("agentCreateQueue", true, false, false);
    }

    @Bean
    public Queue transfertMoneyQueue() {
        return new Queue("transfertMoneyQueue", true, false, false);
    }

    @Bean
    public Binding binding1(TopicExchange clientExchange, Queue clientAccountQueue) {
        return BindingBuilder.bind(clientAccountQueue).to(clientExchange).with("client-account.create");
    }

    @Bean
    public Binding binding2(TopicExchange clientExchange, Queue agentAccountQueue) {
        return BindingBuilder.bind(agentAccountQueue).to(clientExchange).with("agent-account.create");
    }
    
}
