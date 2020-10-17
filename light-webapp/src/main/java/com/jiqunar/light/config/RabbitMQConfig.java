package com.jiqunar.light.config;

import com.jiqunar.light.model.mq.MQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfig {
    @Autowired
    private MQConfig mqConfig;

    @Bean
    public DirectExchange defaultExchange() {
        boolean durable = true;
        boolean autoDelete = false;
        return new DirectExchange(mqConfig.getDefaultExchange(), durable, autoDelete);
    }

    @Bean
    public Queue defaultQueue() {
        boolean durable = true;
        boolean exclusive = false;
        boolean autoDelete = false;
        return new Queue(mqConfig.getDefaultQueue(), durable, exclusive, autoDelete);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(defaultQueue())
                .to(defaultExchange())
                .with(mqConfig.getDefaultRouteKey());
    }



    @Bean
    public Queue alipayBillQueue() {
        boolean durable = true;
        boolean exclusive = false;
        boolean autoDelete = false;
        return new Queue(mqConfig.getAlipayBillQueue(), durable, exclusive, autoDelete);
    }

    @Bean
    public Binding alipayBillBinding() {
        return BindingBuilder.bind(alipayBillQueue())
                .to(alipayBillExchange())
                .with(mqConfig.getAlipayBillRouteKey());
    }

    @Bean
    public DirectExchange alipayBillExchange() {
        boolean durable = true;
        boolean autoDelete = false;
        return new DirectExchange(mqConfig.getAlipayBillExchange(), durable, autoDelete);
    }

    @Bean
    public Queue wepayBillQueue() {
        boolean durable = true;
        boolean exclusive = false;
        boolean autoDelete = false;
        return new Queue(mqConfig.getWepayBillQueue(), durable, exclusive, autoDelete);
    }

    @Bean
    public Binding wepayBillBinding() {
        return BindingBuilder.bind(wepayBillQueue())
                .to(wepayBillExchange())
                .with(mqConfig.getWepayBillRouteKey());
    }

    @Bean
    public DirectExchange wepayBillExchange() {
        boolean durable = true;
        boolean autoDelete = false;
        return new DirectExchange(mqConfig.getWepayBillExchange(), durable, autoDelete);
    }
}
