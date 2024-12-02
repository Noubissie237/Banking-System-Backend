package com.banking_system.service_account_management.config;

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
    public Queue demandeAcceptedQueue() {
        return new Queue("demandeAcceptedQueue", true, false,false);
    }


    @Bean
    public Queue agentAccountCreateQueue() {
        return new Queue("agentAccountCreateQueue", true, false, false);
    }

    @Bean
    public Queue agentCreateAddMatriculeQueue() {
        return new Queue("agentCreateAddMatriculeQueue", true, false, false);
    }

    /* ------------- TRANSFERT -------------- */

    @Bean
    public Queue transfertSendQueue() {
        return new Queue("transfertSendQueue", true, false, false);
    }

    @Bean
    public Queue transfertDoneQueue() {
        return new Queue("transfertDoneQueue", true, false, false);
    }

    @Bean
    public Queue transfertDoneForAgenceQueue() {
        return new Queue("transfertDoneForAgenceQueue", true, false, false);
    }

    @Bean
    public Queue transfertDoneForMessageQueue() {
        return new Queue("transfertDoneForMessageQueue", true, false, false);
    }

    /* --------------------------------------- */

    /*---------------- DEPOT ------------------ */

    @Bean
    public Queue depotSendQueue() {
        return new Queue("depotSendQueue", true, false, false);
    }

    @Bean
    public Queue depotDoneQueue() {
        return new Queue("depotDoneQueue", true, false, false);
    }

    @Bean
    public Queue depotDoneForMessageQueue() {
        return new Queue("depotDoneForMessageQueue", true, false, false);
    }

    // ******************************************

    /* ------------- RETRAIT -------------- */
    @Bean
    public Queue retraitSendQueue() {
        return new Queue("retraitSendQueue", true, false, false);
    }

    @Bean
    public Queue retraitDoneQueue() {
        return new Queue("retraitDoneQueue", true, false, false);
    }

    @Bean
    public Queue retraitDoneForAgenceQueue() {
        return new Queue("retraitDoneForAgenceQueue", true, false, false);
    }

    @Bean
    public Queue retraitm() {
        return new Queue("retraitm", true, false, false);
    }

    /* ------------------------------------- */
    

    @Bean
    public Queue rechargeByAgence() {
        return new Queue("rechargeByAgence", true, false, false);
    }


    @Bean
    public Queue rechargeByAgenceDone() {
        return new Queue("rechargeByAgenceDone", true, false, false);
    }

    @Bean
    public Queue clientAccountQueueMessage() {
        return new Queue("clientAccountQueueMessage", true, false, false);
    }


    @Bean
    public Binding binding1(TopicExchange clientExchange, Queue clientAccountQueueMessage) {
        return BindingBuilder.bind(clientAccountQueueMessage).to(clientExchange).with("client-account.create.message");
    }

    @Bean
    public Binding binding2(TopicExchange clientExchange, Queue agentAccountCreateQueue) {
        return BindingBuilder.bind(agentAccountCreateQueue).to(clientExchange).with("agent-account.create");
    }
  
    @Bean
    public Binding binding3(TopicExchange transactionExchange, Queue rechargeByAgenceDone) {
        return BindingBuilder.bind(rechargeByAgenceDone).to(transactionExchange).with("recharge.done");
    }

    @Bean
    public Binding binding5(TopicExchange transactionExchange, Queue retraitDoneQueue) {
        return BindingBuilder.bind(retraitDoneQueue).to(transactionExchange).with("retrait.done");
    }

    @Bean
    public Binding binding6(TopicExchange transactionExchange, Queue retraitDoneForAgenceQueue) {
        return BindingBuilder.bind(retraitDoneForAgenceQueue).to(transactionExchange).with("retrait.done.agence");
    }

    @Bean
    public Binding binding7(TopicExchange transactionExchange, Queue transfertDoneQueue) {
        return BindingBuilder.bind(transfertDoneQueue).to(transactionExchange).with("transfert.done");
    }

    @Bean
    public Binding binding8(TopicExchange transactionExchange, Queue transfertDoneForAgenceQueue) {
        return BindingBuilder.bind(transfertDoneForAgenceQueue).to(transactionExchange).with("transfert.done.agence");
    }


    @Bean
    public Binding binding9(TopicExchange transactionExchange, Queue depotDoneQueue) {
        return BindingBuilder.bind(depotDoneQueue).to(transactionExchange).with("depot.done");
    }

    @Bean
    public Binding binding10(TopicExchange transactionExchange, Queue transfertDoneForMessageQueue) {
        return BindingBuilder.bind(transfertDoneForMessageQueue).to(transactionExchange).with("transfert.done.message");
    }

    @Bean
    public Binding binding11(TopicExchange transactionExchange, Queue retraitm) {
        return BindingBuilder.bind(retraitm).to(transactionExchange).with("retrait.done.message");
    }

    @Bean
    public Binding binding12(TopicExchange transactionExchange, Queue depotDoneForMessageQueue) {
        return BindingBuilder.bind(depotDoneForMessageQueue).to(transactionExchange).with("depot.done.message");
    }

}
