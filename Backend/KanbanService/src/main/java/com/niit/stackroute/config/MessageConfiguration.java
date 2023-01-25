package com.niit.stackroute.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MessageConfiguration
{
    private String exchangeName = "task_exchange";
    private String registerQueue1 = "task_queue1";
    private String registerQueue2 = "task_queue2";
    private String registerQueue3 = "task_queue3";
    private String registerQueue4 = "task_queue4";
    private String registerQueue5 = "task_queue5";



    public static final String getNotiQueue = "noti_get_queue";
    public static final String getByIdNotiQueue = "noti_getbyid_queue";

    public static final String notiRoutingKey = "routing_noti_token";
    public static final String notiRoutingKey2 = "routing_noti_token2";

    @Bean
    public DirectExchange directExchange()
    {
        return new DirectExchange(exchangeName);
    }

    //for task service
    @Bean
    @Primary
    public Queue registerQueue1()
    {
        return new Queue(registerQueue1,false);
    }

    @Bean
    public Queue registerQueue2()
    {
        return new Queue(registerQueue2,false);
    }

    @Bean
    public Queue registerQueue3()
    {
        return new Queue(registerQueue3,false);
    }

    @Bean
    public Queue registerQueue4()
    {
        return new Queue(registerQueue4,false);
    }

    @Bean
    public Queue registerQueue5()
    {
        return new Queue(registerQueue5,false);
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter()
    {
        return new  Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory)
    {
        RabbitTemplate rabbitTemp=new RabbitTemplate(connectionFactory);
        rabbitTemp.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemp;
    }

    @Bean
    Binding bindingUser(Queue registerQueue, DirectExchange exchange)
    {
        return BindingBuilder.bind(registerQueue1()).to(exchange).with("task_queue1");
    }

    @Bean
    Binding bindingUser2(Queue registerQueue, DirectExchange exchange)
    {
        return BindingBuilder.bind(registerQueue2()).to(exchange).with("task_queue2");
    }

    @Bean
    Binding bindingUser3(Queue registerQueue, DirectExchange exchange)
    {
        return BindingBuilder.bind(registerQueue3()).to(exchange).with("task_queue3");
    }

    @Bean
    Binding bindingUser4(Queue registerQueue, DirectExchange exchange)
    {
        return BindingBuilder.bind(registerQueue4()).to(exchange).with("task_queue4");
    }

    @Bean
    Binding bindingUser5(Queue registerQueue, DirectExchange exchange)
    {
        return BindingBuilder.bind(registerQueue5()).to(exchange).with("task_queue5");
    }

    //for notification
    @Bean
    public Queue getQueue(){return new Queue(getNotiQueue,false); }

    @Bean
    public Queue getByIdQueue(){return new Queue(getByIdNotiQueue,false); }

    @Bean
    Binding bindingNotification(Queue getQueue,DirectExchange exchange)
    {
        return BindingBuilder.bind(getQueue()).to(exchange).with("routing_noti_token");
    }

    @Bean
    Binding bindingNotification2(Queue getByIdQueue,DirectExchange exchange)
    {
        return BindingBuilder.bind(getByIdQueue()).to(exchange).with("routing_noti_token2");
    }



}
