package com.idrive.driveronboardingservice.queue;

import com.idrive.driveronboardingservice.model.VerificationMessage;
import com.idrive.driveronboardingservice.worklow.WorkflowExecutor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VerificationMessageConsumer {

    @Autowired
    WorkflowExecutor workflowExecutor;


    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consumeJsonMessage(VerificationMessage message){
        log.info(String.format("Received message -> %s", message.toString()));
        workflowExecutor.execute();

    }
}