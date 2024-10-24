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
//pour pouvoir utiliser les bean
@EnableRabbit
public class RabbitConfig {
    
    @Bean
    //convrtir el json en message(jsonMesagecoverter car rabbit mannipule les messages)
    public Jackson2JsonMessageConverter jsonMessageConverter()
    {
        //renvoit le json en message
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        return rabbitTemplate;
    }

    @Bean
    //echange topic(creer les messages qui peuvent aller a tout le monde)
    //cree un couloir
    public TopicExchange clientExchange(){
        return new TopicExchange("clientExchange");
    }

    @Bean
    //cree une cle a notre couloir
    public Queue clientQueue(){
        return new Queue("clientQueue");
    }

    @Bean
    //cree le canal d'echange
    public Binding binding(TopicExchange clientExchange, Queue clientQueue) {
        return BindingBuilder.bind(clientQueue).to(clientExchange).with("client.create");
    }

}
