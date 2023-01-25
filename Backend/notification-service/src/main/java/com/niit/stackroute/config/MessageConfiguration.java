package com.niit.stackroute.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MessageConfiguration {

    public static final String directExchangeName = "task_exchange";
    public static final String getNotiQueue = "noti_get_queue";
    public static final String getByIdNotiQueue = "noti_getbyid_queue";

    public static final String notiRoutingKey = "routing_noti_token";
    public static final String notiRoutingKey2 = "routing_noti_token2";

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter()
    {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue queue(){return new Queue(getNotiQueue,false);}

    @Bean
    @Primary
    public Queue queue2(){return new Queue(getByIdNotiQueue,false);}

    @Bean
    public DirectExchange directExchange(){return new DirectExchange(directExchangeName);}


    @Bean
    Binding binding(Queue queue, DirectExchange exchange)
    {
        return BindingBuilder.bind(queue).to(exchange).with(notiRoutingKey);
    }
    @Bean
    Binding binding2(Queue queue2, DirectExchange exchange)
    {
        return BindingBuilder.bind(queue2).to(exchange).with(notiRoutingKey2);
    }


}
