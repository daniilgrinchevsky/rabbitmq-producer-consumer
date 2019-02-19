package com.example.producer.sender;

import com.example.producer.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqSender {
    private static final Logger log = LoggerFactory.getLogger(RabbitMqSender.class);

    @Autowired
    RabbitTemplate rabbitTemplate;


    @Scheduled(fixedRate = 1000)
    public String send() {
        Message message = new Message();
        rabbitTemplate.convertAndSend(message);
        log.info("Sending to exchange " + message);
        return "Sending to exchange";
    }

}
