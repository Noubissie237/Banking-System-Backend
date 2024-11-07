package com.banking_system.service_tranfert.config;

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
    public Queue transfertMoneyQueue() {
        return new Queue("transfertMoneyQueue", true, false, false);
    }

    @Bean
    public Queue transfertMoneyQueueAgence() {
        return new Queue("transfertMoneyQueueAgence", true, false, false);
    }

    @Bean
    public Queue transfertMoneyQueue1() {
        return new Queue("transfertMoneyQueue1", true, false, false);
    }

    @Bean
    public Binding binding1(TopicExchange transactionExchange, Queue transfertMoneyQueue) {
        return BindingBuilder.bind(transfertMoneyQueue).to(transactionExchange).with("transfert.send");
    }

    @Bean
    public Binding binding2(TopicExchange transactionExchange, Queue transfertMoneyQueueAgence) {
        return BindingBuilder.bind(transfertMoneyQueueAgence).to(transactionExchange).with("transfert.send.agence");
    }

    @Bean
    public Binding binding3(TopicExchange transactionExchange, Queue transfertMoneyQueue1) {
        return BindingBuilder.bind(transfertMoneyQueue1).to(transactionExchange).with("transfert.send");
    }

}
