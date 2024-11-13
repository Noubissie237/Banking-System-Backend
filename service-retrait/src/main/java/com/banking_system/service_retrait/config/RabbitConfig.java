package com.banking_system.service_retrait.config;

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
    public TopicExchange transactionExchange(){
        return new TopicExchange("transactionExchange", true, false);
    }

    @Bean
    public Queue retraitMoneyQueue() {
        return new Queue("retraitMoneyQueue", true, false, false);
    }

    @Bean
    public Queue retraitAgenceQueue(){
        return new Queue("retraitAgenceQueue", true, false, false);
    }

    @Bean
    public Queue retraitMoneyQueueForTransactions() {
        return new Queue("retraitMoneyQueueForTransactions", true, false, false);
    }


    @Bean
    public Binding bindingRetrait(TopicExchange transactionExchange, Queue retraitMoneyQueue) {
        return BindingBuilder.bind(retraitMoneyQueue).to(transactionExchange).with("retrait.send");
    }

    @Bean
    public Binding bindingRetraitAgence(TopicExchange transactionExchange, Queue retraitAgenceQueue) {
        return BindingBuilder.bind(retraitAgenceQueue).to(transactionExchange).with("retrait.send.agence");
    }
    @Bean
    public Binding bindingRetraitForTransactions(TopicExchange transactionExchange, Queue retraitMoneyQueueForTransactions) {
        return BindingBuilder.bind(retraitMoneyQueueForTransactions).to(transactionExchange).with("retrait.send");
    }


}



    
