package com.banking_system.service_users.config;

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
    public TopicExchange clientExchange(){
        return new TopicExchange("clientExchange", true, false);
    }

    @Bean
    public TopicExchange transactionExchange(){
        return new TopicExchange("transactionExchange", true, false);
    }

    @Bean
    public Queue clientCreateQueue(){
        return new Queue("clientCreateQueue", true, false, false);
    }

    @Bean
    public Queue clientAccountCreateQueue() {
        return new Queue("clientAccountCreateQueue", true, false, false);
    }


    @Bean
    public Queue agentCreateNoMatriculeQueue() {
        return new Queue("agentCreateNoMatriculeQueue", true, false, false);
    }

    @Bean
    public Queue agentCreateAddMatriculeQueue() {
        return new Queue("agentCreateAddMatriculeQueue", true, false, false);
    }

    @Bean
    public Binding binding1(TopicExchange clientExchange, Queue clientCreateQueue) {
        return BindingBuilder.bind(clientCreateQueue).to(clientExchange).with("client.create");
    }

    @Bean
    public Binding binding2(TopicExchange clientExchange, Queue agentCreateAddMatriculeQueue) {
        return BindingBuilder.bind(agentCreateAddMatriculeQueue).to(clientExchange).with("agent.create");
    }

}
