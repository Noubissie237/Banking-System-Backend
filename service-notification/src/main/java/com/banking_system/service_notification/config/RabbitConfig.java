package com.banking_system.service_notification.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Binding;

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
    public Queue rejectDemandeQueue() {
        return new Queue("rejectDemandeQueue", true, false, false);
    }

    @Bean
    public Queue transfertMoneyQueue() {
        return new Queue("transfertMoneyQueue", true, false, false);
    }
    @Bean
    public Queue rechargeByAgence() {
        return new Queue("rechargeByAgenceo", true, false, false);
    }

    @Bean
    public Queue depotMoneyQueueForEven() {
        return new Queue("depotMoneyQueueForEven", true, false, false);
    }
    @Bean
    public Queue depotMoney() {
        return new Queue("depotMoney", true, false, false);
    }
    

    @Bean
    public Queue retraitm() {
        return new Queue("retraitm", true, false, false);
    }
    
    @Bean
    public Queue transfertm() {
        return new Queue("transfertm", true, false, false);
    }
    
    // @Bean
    // public Queue depotmessageQueue() {
    //     return new Queue("depotmessageQueue", true, false, false);
    // }
   
    @Bean
    public Queue depotMoneyQueueForEvent() {
        return new Queue("depotMoneyQueueForEvent", true, false, false);
    }

    @Bean
    public Queue transfertmessageQueue() {
        return new Queue("transfertmessageQueue", true, false, false);
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
