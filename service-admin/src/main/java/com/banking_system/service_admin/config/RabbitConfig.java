package com.banking_system.service_admin.config;

import org.springframework.amqp.core.Binding;
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

import com.banking_system.service_admin.broker.ClientEventConsumer;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

@Configuration
@EnableRabbit
public class RabbitConfig {

    @Bean
    public Queue clientQueue() {
        return new Queue("clientQueue");
    }

    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
            MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames("clientQueue");
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(ClientEventConsumer consumer) {
        MessageListenerAdapter adapter = new MessageListenerAdapter(consumer, "receiveClientEvent");
        adapter.setMessageConverter(converter());
        return adapter;
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
        return new TopicExchange("clientExchange", true, false);
    }

    @Bean
    public Queue demandeQueue() {
        return new Queue("demandeQueue", true, false, false);
    }

    @Bean
    public Binding binding(TopicExchange clientExchange, Queue demandeQueue) {
        return BindingBuilder.bind(demandeQueue).to(clientExchange).with("demande.accepted");
    }
}
