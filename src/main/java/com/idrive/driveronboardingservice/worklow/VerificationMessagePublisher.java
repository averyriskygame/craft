package com.idrive.driveronboardingservice.worklow;

import com.idrive.driveronboardingservice.model.Verification;
import com.idrive.driveronboardingservice.model.VerificationMessage;
import com.idrive.driveronboardingservice.queue.VerificationMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VerificationMessagePublisher {

    @Autowired
    VerificationMessageProducer verificationMessageProducer;

    public void publish(Verification verification,String requestId) {
        VerificationMessage verificationMessage = new VerificationMessage(requestId,verification.getVerificationId());
        verificationMessageProducer.sendJsonMessage(verificationMessage);
    }
}
