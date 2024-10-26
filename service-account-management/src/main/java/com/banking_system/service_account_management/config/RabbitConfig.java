package com.banking_system.service_account_management.config;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.banking_system.service_account_management.broker.AccountConsumer;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

import org.springframework.amqp.core.Binding;


@Configuration
@EnableRabbit
public class RabbitConfig {
    
    @Bean
    public Queue demandeQueue() {
        return new Queue("demandeQueue");
    }

    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames("demandeQueue");
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(AccountConsumer consumer){
        return new MessageListenerAdapter(consumer, "receiveAccountEvent");
    }

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
    public Queue accountQueue() {
        return new Queue("accountQueue", true, false, false);
    }

    @Bean
    public Binding binding(TopicExchange clientExchange, Queue accountQueue) {
        return BindingBuilder.bind(accountQueue).to(clientExchange).with("account.create");
    }
    
}
