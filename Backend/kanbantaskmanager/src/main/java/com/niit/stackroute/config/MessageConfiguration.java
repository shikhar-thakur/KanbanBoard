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
public class MessageConfiguration
{
    public static final String directExchangeName = "task_exchange";
    public static final String queueName1 = "task_queue1";
    public static final String queueName2 = "task_queue2";
    public static final String queueName3 = "task_queue3";
    public static final String queueName4 = "task_queue4";
    public static final String queueName5 = "task_queue5";

    public static final String routingKey1 = "routing_token1";
    public static final String routingKey2 = "routing_token2";
    public static final String routingKey3 = "routing_token3";
    public static final String routingKey4 = "routing_token4";
    public static final String routingKey5 = "routing_token5";

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter()
    {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    @Primary
    Queue queue()
    {
        return new Queue(queueName1, false);
    }

    @Bean
    Queue queue1()
    {
        return new Queue(queueName2, false);
    }

    @Bean
    Queue queue3()
    {
        return new Queue(queueName3, false);
    }

    @Bean
    Queue queue4()
    {
        return new Queue(queueName4, false);
    }

    @Bean
    Queue queue5()
    {
        return new Queue(queueName5, false);
    }

    @Bean
    DirectExchange exchange()
    {
        return new DirectExchange(directExchangeName);
    }



    @Bean
    Binding binding1(Queue queue, DirectExchange exchange)
    {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey1);
    }


    @Bean
    Binding binding2(Queue queue2 ,DirectExchange exchange)
    {
        return BindingBuilder.bind(queue2).to(exchange).with(routingKey2);
    }

    @Bean
    Binding binding3(Queue queue3 ,DirectExchange exchange)
    {
        return BindingBuilder.bind(queue3).to(exchange).with(routingKey3);
    }

    @Bean
    Binding binding4(Queue queue4 ,DirectExchange exchange)
    {
        return BindingBuilder.bind(queue4).to(exchange).with(routingKey4);
    }

    @Bean
    Binding binding5(Queue queue5 ,DirectExchange exchange)
    {
        return BindingBuilder.bind(queue5).to(exchange).with(routingKey5);
    }
}
