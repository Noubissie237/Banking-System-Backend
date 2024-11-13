package com.banking_system.service_admin.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
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
    public TopicExchange clientExchange() {
        return new TopicExchange("clientExchange", true, false);
    }

    @Bean
    public TopicExchange transactionExchange(){
        return new TopicExchange("transactionExchange", true, false);
    }

    @Bean
    public Queue clientQueue() {
        return new Queue("clientQueue", true, false, false);
    }

    @Bean
    public Queue acceptDemandeQueue() {
        return new Queue("acceptDemandeQueue", true, false, false);
    }

    @Bean
    public Queue rejectDemandeQueue() {
        return new Queue("rejectDemandeQueue", true, false, false);
    }

    @Bean
    public Queue agentQueue() {
        return new Queue("agentQueue", true, false, false);
    }

    @Bean
    public Queue transfertMoneyQueueAgence() {
        return new Queue("transfertMoneyQueueAgence", true, false, false);
    }

    @Bean
    public Queue retraitAgenceQueue(){
        return new Queue("retraitAgenceQueue", true, false, false);
    }

    @Bean
    public Queue rechargeByAgence() {
        return new Queue("rechargeByAgence", true, false, false);
    }

    @Bean
    public Binding binding1(TopicExchange clientExchange, Queue acceptDemandeQueue) {
        return BindingBuilder.bind(acceptDemandeQueue).to(clientExchange).with("demande.accepted");
    }

    @Bean
    public Binding binding2(TopicExchange clientExchange, Queue rejectDemandeQueue) {
        return BindingBuilder.bind(rejectDemandeQueue).to(clientExchange).with("demande.reject");
    }

    @Bean
    public Binding binding3(TopicExchange clientExchange, Queue agentQueue) {
        return BindingBuilder.bind(agentQueue).to(clientExchange).with("agent.demande");
    }

    @Bean
    public Binding binding4(TopicExchange transactionExchange, Queue rechargeByAgence) {
        return BindingBuilder.bind(rechargeByAgence).to(transactionExchange).with("recharge.send");
    }

}
