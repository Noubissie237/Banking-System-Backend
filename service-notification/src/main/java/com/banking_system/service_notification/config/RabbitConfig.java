package com.banking_system.service_notification.config;

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
    public Jackson2JsonMessageConverter jsonMessageConverter()
    {
        return new Jackson2JsonMessageConverter();
    }


    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
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
    public Queue clientAccountCreateForMessageQueue() {
        return new Queue("clientAccountCreateForMessageQueue", true, false, false);
    }

    @Bean
    public Queue agentAccountCreateForMessageQueue() {
        return new Queue("agentAccountCreateForMessageQueue", true, false, false);
    }


    @Bean
    public Queue demandeRejectForMessageQueue() {
        return new Queue("demandeRejectForMessageQueue", true, false, false);
    }


    @Bean
    public Queue rechargeDoneForMessageQueue() {
        return new Queue("rechargeDoneForMessageQueue", true, false, false);
    }

    @Bean
    public Queue depotDoneForMessageQueue() {
        return new Queue("depotDoneForMessageQueue", true, false, false);
    }

    @Bean
    public Queue retraitDoneForMessageQueue() {
        return new Queue("retraitDoneForMessageQueue", true, false, false);
    }
    
    @Bean
    public Queue transfertDoneForMessageQueue() {
        return new Queue("transfertDoneForMessageQueue", true, false, false);
    }

}
