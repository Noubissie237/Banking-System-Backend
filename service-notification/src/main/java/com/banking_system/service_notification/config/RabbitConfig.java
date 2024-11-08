package com.banking_system.service_notification.config;

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
        return new Queue("acceptDemandeQueue");
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
    public Queue transfertmessageQueue() {
        return new Queue("transfertmessageQueue", true, false, false);
    }
    @Bean
    public Queue depotmessageQueue() {
        return new Queue("depotmessageQueue", true, false, false);
    }
    @Bean
    public Queue retraitmessageQueue() {
        return new Queue("retraitmessageQueue", true, false, false);
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
    public Binding binding3(TopicExchange transactionExchange, Queue transfertmessageQueue) {
        return BindingBuilder.bind(transfertmessageQueue).to(transactionExchange).with("transfertenvoyeurmessage");
    }

    @Bean
    public Binding binding4(TopicExchange transactionExchange, Queue transfertmessageQueue) {
        return BindingBuilder.bind(transfertmessageQueue).to(transactionExchange).with("transfertrecepteurmessage");
    }
    @Bean
    public Binding binding5(TopicExchange transactionExchange, Queue depotmessageQueue) {
        return BindingBuilder.bind(depotmessageQueue).to(transactionExchange).with("depotenvoyeurmessage");
    }
    @Bean
    public Binding binding6(TopicExchange transactionExchange, Queue depotmessageQueue) {
        return BindingBuilder.bind(depotmessageQueue).to(transactionExchange).with("depotrecepteurmessage");
    }
    @Bean
    public Binding binding7(TopicExchange transactionExchange, Queue retraitmessageQueue) {
        return BindingBuilder.bind(retraitmessageQueue).to(transactionExchange).with("retraitclientmessage");
    }
    @Bean
    public Binding binding8(TopicExchange transactionExchange, Queue retraitmessageQueue) {
        return BindingBuilder.bind(retraitmessageQueue).to(transactionExchange).with("retraitagentmessage");
    }
    
}
