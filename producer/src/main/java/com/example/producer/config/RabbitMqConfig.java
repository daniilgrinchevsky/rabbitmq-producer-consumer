package com.example.producer.config;

import com.example.producer.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.HashMap;
import java.util.Map;

@EnableScheduling
@Configuration
public class RabbitMqConfig {
    private static final Logger log = LoggerFactory.getLogger(RabbitMqConfig.class);

    @Bean
    public ConnectionFactory connectionFactory() {

        return new CachingConnectionFactory("localhost");
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setExchange("exchange");
        template.setMessageConverter(messageConverter());
        return template;
    }

    @Bean
    public FanoutExchange fanoutExchangeA(){
        return new FanoutExchange("exchange");
    }

    @Bean
    public Queue myQueue() {
        return new Queue("queue");
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(myQueue()).to(fanoutExchangeA());
    }

    @Bean
    public MessageConverter messageConverter()
    {
        Jackson2JsonMessageConverter jsonMessageConverter = new Jackson2JsonMessageConverter();
        jsonMessageConverter.setClassMapper(classMapper());
        return jsonMessageConverter;
    }

    @Bean
    public DefaultClassMapper classMapper()
    {
        DefaultClassMapper classMapper = new DefaultClassMapper();
        Map<String, Class<?>> idClassMapping = new HashMap<>();
        idClassMapping.put("com.test.rabbitmq.Message", Message.class);
        classMapper.setIdClassMapping(idClassMapping);
        return classMapper;
    }}
