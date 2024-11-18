package com.banking_system.service_account_management.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


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
        return new Queue("acceptDemandeQueue", true, false,false);
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
    public Queue depotMoneyQueue() {
        return new Queue("depotMoneyQueue", true, false, false);
    }

    @Bean
    public Queue depotMoneyQueueForEvent() {
        return new Queue("depotMoneyQueueForEvent", true, false, false);
    }

    @Bean
    public Queue retraitMoneyQueue() {
        return new Queue("retraitMoneyQueue", true, false, false);
    }
    
    @Bean
    public Queue rechargeAccountQueue() {
        return new Queue("transfertMoneyQueue", true, false, false);
    }

    @Bean
    public Queue rechargeByAgence() {
        return new Queue("rechargeByAgence", true, false, false);
    }

    @Bean
    public Queue rechargeByAgenceo() {
        return new Queue("rechargeByAgenceo", true, false, false);
    }
    @Bean
    public Queue depotMoney() {
        return new Queue("depotMoney", true, false, false);
    }

    @Bean
    public Queue retraitm() {
        return new Queue("retraitm", true, false, false);
    }
    
    @Bean
    public Queue transfertm() {
        return new Queue("transfertm", true, false, false);
    }

    @Bean
    public Binding binding1(TopicExchange clientExchange, Queue clientAccountQueue) {
        return BindingBuilder.bind(clientAccountQueue).to(clientExchange).with("client-account.create");
    }

    @Bean
    public Binding binding2(TopicExchange clientExchange, Queue agentAccountQueue) {
        return BindingBuilder.bind(agentAccountQueue).to(clientExchange).with("agent-account.create");
    }

    @Bean
    public Binding binding3(TopicExchange transactionExchange, Queue rechargeByAgenceo) {
        return BindingBuilder.bind(rechargeByAgenceo).to(transactionExchange).with("recharge.agence");
    }

    @Bean
    public Binding binding5(TopicExchange transactionExchange, Queue rechargeByAgence) {
        return BindingBuilder.bind(rechargeByAgence).to(transactionExchange).with("recharge.send.agence");
    }

    @Bean
    public Binding binding4(TopicExchange transactionExchange, Queue depotMoneyQueueForEvent) {
        return BindingBuilder.bind(depotMoneyQueueForEvent).to(transactionExchange).with("getting.depot");
    }

    @Bean
    public Binding binding6(TopicExchange transactionExchange, Queue depotMoney) {
        return BindingBuilder.bind(depotMoney).to(transactionExchange).with("depot.m");

    }
    @Bean
    public Binding binding7(TopicExchange transactionExchange, Queue retraitm) {
        return BindingBuilder.bind(retraitm).to(transactionExchange).with("retrait.m");

    }

    @Bean
    public Binding binding8(TopicExchange transactionExchange, Queue transfertm) {
        return BindingBuilder.bind(transfertm).to(transactionExchange).with("transfert.m");

    }
    
}
