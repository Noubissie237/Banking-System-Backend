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
    public Queue clientQueue(){
        return new Queue("clientQueue", true, false, false);
    }

    @Bean
    public Queue clientAccountQueue() {
        return new Queue("clientAccountQueue", true, false, false);
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
    public Queue agentCreateQueue() {
        return new Queue("agentCreateQueue", true, false, false);
    }

    @Bean
    public Queue agentAccountQueue() {
        return new Queue("agentAccountQueue", true, false, false);
    }

    @Bean
    public Queue transfertMoneyQueue() {
        return new Queue("transfertMoneyQueue", true, false, false);
    }

    @Bean
    public Binding binding1(TopicExchange clientExchange, Queue clientQueue) {
        return BindingBuilder.bind(clientQueue).to(clientExchange).with("client.create");
    }

    @Bean
    public Binding binding2(TopicExchange clientExchange, Queue agentCreateQueue) {
        return BindingBuilder.bind(agentCreateQueue).to(clientExchange).with("agent.create");
    }

    @Bean
    public Binding binding3(TopicExchange transactionExchange, Queue transfertMoneyQueue) {
        return BindingBuilder.bind(transfertMoneyQueue).to(transactionExchange).with("start-transfert");
    }

}
