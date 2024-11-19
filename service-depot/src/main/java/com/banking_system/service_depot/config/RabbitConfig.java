package com.banking_system.service_depot.config;

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
    public Queue depotSendQueue() {
        return new Queue("depotSendQueue", true, false, false);
    }
    @Bean
    public Queue depotMoney() {
        return new Queue("depotMoney", true, false, false);
    }


    @Bean
    public Binding binding1(TopicExchange transactionExchange, Queue depotSendQueue) {
        return BindingBuilder.bind(depotSendQueue).to(transactionExchange).with("depot.send");
    }
    @Bean
    public Binding binding6(TopicExchange transactionExchange, Queue depotMoney) {
        return BindingBuilder.bind(depotMoney).to(transactionExchange).with("depot.m");

    }

}
