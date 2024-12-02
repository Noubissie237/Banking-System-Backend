package com.banking_system.service_admin.config;

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
        return new TopicExchange("clientExchange", true, false);
    }

    @Bean
    public TopicExchange transactionExchange(){
        return new TopicExchange("transactionExchange", true, false);
    }

    @Bean
    public Queue clientCreateQueue() {
        return new Queue("clientCreateQueue", true, false, false);
    }

    @Bean
    public Queue demandeAcceptedQueue() {
        return new Queue("demandeAcceptedQueue", true, false, false);
    }

    @Bean
    public Queue rejectDemandeQueue() {
        return new Queue("rejectDemandeQueue", true, false, false);
    }

    @Bean
    public Queue agentCreateNoMatriculeQueue() {
        return new Queue("agentCreateNoMatriculeQueue", true, false, false);
    }

    @Bean
    public Queue transfertDoneForAgenceQueue() {
        return new Queue("transfertDoneForAgenceQueue", true, false, false);
    }

    @Bean
    public Queue retraitDoneForAgenceQueue() {
        return new Queue("retraitDoneForAgenceQueue", true, false, false);
    }

    @Bean
    public Queue rechargeByAgenceSendQueue() {
        return new Queue("rechargeByAgenceSendQueue", true, false, false);
    }

    @Bean
    public Queue rechargeDoneForMessageQueue() {
        return new Queue("rechargeDoneForMessageQueue", true, false, false);
    }

    @Bean
    public Binding binding1(TopicExchange clientExchange, Queue demandeAcceptedQueue) {
        return BindingBuilder.bind(demandeAcceptedQueue).to(clientExchange).with("demande.accepted");
    }

    @Bean
    public Binding binding2(TopicExchange clientExchange, Queue rejectDemandeQueue) {
        return BindingBuilder.bind(rejectDemandeQueue).to(clientExchange).with("demande.reject");
    }

    @Bean
    public Binding binding3(TopicExchange clientExchange, Queue agentCreateNoMatriculeQueue) {
        return BindingBuilder.bind(agentCreateNoMatriculeQueue).to(clientExchange).with("agent.demande");
    }

    @Bean
    public Binding binding4(TopicExchange transactionExchange, Queue rechargeByAgenceSendQueue) {
        return BindingBuilder.bind(rechargeByAgenceSendQueue).to(transactionExchange).with("recharge.send");
    }

    @Bean
    public Binding binding5(TopicExchange transactionExchange, Queue rechargeDoneForMessageQueue) {
        return BindingBuilder.bind(rechargeDoneForMessageQueue).to(transactionExchange).with("recharge.sen");
    }


    
}
