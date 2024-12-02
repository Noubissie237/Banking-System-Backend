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
    public Queue clientAccountQueueMessage() {
        return new Queue("clientAccountQueueMessage", true, false, false);
    }

    @Bean
    public Queue agentAccountCreateQueue() {
        return new Queue("agentAccountCreateQueue", true, false, false);
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
    public Queue rejectDemandeQueue() {
        return new Queue("rejectDemandeQueue", true, false, false);
    }


    @Bean
    public Queue rechargeByAgenceo() {
        return new Queue("rechargeByAgenceo", true, false, false);
    }

    @Bean
    public Queue depotm() {
        return new Queue("depotm", true, false, false);
    }

    @Bean
    public Queue retraitm() {
        return new Queue("retraitm", true, false, false);
    }
    
    @Bean
    public Queue transfertm() {
        return new Queue("transfertm", true, false, false);
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
    public Queue compterejet(){
        return new Queue("compterejet", true, false, false);
    }

    @Bean
    public Queue comptecreatclient(){
        return new Queue("comptecreatclient", true, false, false);
    }

    @Bean
    public Queue comptecreateagent(){
        return new Queue("comptecreateagent", true, false, false);
    }


    @Bean
    public Binding binding1(TopicExchange clientExchange, Queue compterejet){
        return  BindingBuilder.bind(compterejet).to(clientExchange).with("account.rejet");
    }
 
    @Bean
    public Binding binding2(TopicExchange clientExchange, Queue comptecreatclient){
        return  BindingBuilder.bind(comptecreatclient).to(clientExchange).with("client-account-create");
    }
 
    @Bean
    public Binding binding6(TopicExchange clientExchange, Queue comptecreateagent){
        return  BindingBuilder.bind(comptecreateagent).to(clientExchange).with("agent-account-create");
    }
    @Bean
    public Binding binding3(TopicExchange transactionExchange, Queue transfertmessageQueue) {
        return BindingBuilder.bind(transfertmessageQueue).to(transactionExchange).with("transfertenvoyeurmessage");
    }

    @Bean
    public Binding binding5(TopicExchange transactionExchange, Queue depotmessageQueue) {
        return BindingBuilder.bind(depotmessageQueue).to(transactionExchange).with("depotenvoyeurmessage");
    }
   
    @Bean
    public Binding binding7(TopicExchange transactionExchange, Queue retraitmessageQueue) {
        return BindingBuilder.bind(retraitmessageQueue).to(transactionExchange).with("retraitclientmessage");
    }
    @Bean
    public Binding binding8(TopicExchange transactionExchange, Queue retraitmessageQueue) {
        return BindingBuilder.bind(retraitmessageQueue).to(transactionExchange).with("retraitconfimessage");
    }
   
}
