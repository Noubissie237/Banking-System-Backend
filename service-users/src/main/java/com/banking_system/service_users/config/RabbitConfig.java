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
    // transformer le json en message car rabbit lit les message et non le event json qui sera envoyer par evenement
    @Bean
    //convrtir el json en message(jsonMesagecoverter car rabbit mannipule les messages)
    public Jackson2JsonMessageConverter jsonMessageConverter()
    { // permet de convertir le json en message
        return new Jackson2JsonMessageConverter();
    }

//rabbit cree la connexion entre notre production avec rabbit(qui est le canal)
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        return rabbitTemplate;
    }
//topic va permettre aux diff consommateur de recevoir le meme message
    @Bean
    //echange topic(creer les messages qui peuvent aller a tout le monde)
    //cree un couloir
    public TopicExchange clientExchange(){
        return new TopicExchange("clientExchange");
    }
// la queue va permttre de creer un queue ou sera enregistre notre message qui viendra sous la forme message et non json
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
