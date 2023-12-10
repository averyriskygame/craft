package com.idrive.driveronboardingservice.queue;

import com.idrive.driveronboardingservice.dto.VerificationDTO;
import com.idrive.driveronboardingservice.mapper.VerificationMapper;
import com.idrive.driveronboardingservice.model.Verification;
import com.idrive.driveronboardingservice.model.VerificationMessage;
import com.idrive.driveronboardingservice.model.type.VerificationWorkFlowState;
import com.idrive.driveronboardingservice.service.VerificationService;
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

    @Autowired
    VerificationService verificationService;


    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consumeJsonMessage(VerificationMessage message) throws Exception {
        log.info(String.format("Received message -> %s", message.toString()));
        VerificationDTO verification=verificationService.getVerification(message.getVerificationId());
        workflowExecutor.execute(verification,message.getRequestId());

    }
}