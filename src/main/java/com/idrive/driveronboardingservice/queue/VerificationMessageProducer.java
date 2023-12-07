package com.idrive.driveronboardingservice.queue;

import com.idrive.driveronboardingservice.model.VerificationMessage;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VerificationMessageProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(VerificationMessageProducer.class);

    private AmqpTemplate rabbitTemplate;

    @Autowired
    DelayedMessagePostProcessor messagePostProcessor;

    public VerificationMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(VerificationMessage verificationMessage) {
        log.info(String.format("VerificationMessage sent -> %s  ", verificationMessage.toString()));
        rabbitTemplate.convertAndSend(exchange, routingKey, verificationMessage, messagePostProcessor);


    }


}