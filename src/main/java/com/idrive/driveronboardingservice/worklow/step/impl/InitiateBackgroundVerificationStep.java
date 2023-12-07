package com.idrive.driveronboardingservice.worklow.step.impl;

import com.idrive.driveronboardingservice.model.Verification;
import com.idrive.driveronboardingservice.model.VerificationMessage;
import com.idrive.driveronboardingservice.model.type.VerificationStatus;
import com.idrive.driveronboardingservice.model.type.VerificationWorkFlowState;
import com.idrive.driveronboardingservice.queue.VerificationMessageProducer;
import com.idrive.driveronboardingservice.service.ThirdPartyVerificationService;
import com.idrive.driveronboardingservice.service.VerificationService;
import com.idrive.driveronboardingservice.worklow.VerificationMessagePublisher;
import com.idrive.driveronboardingservice.worklow.model.WorkflowContext;
import com.idrive.driveronboardingservice.worklow.step.WorkflowStep;
import com.idrive.driveronboardingservice.worklow.step.utill.VerificationWorklowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class InitiateBackgroundVerificationStep extends WorkFlowStepImpl {

    @Autowired
    VerificationMessagePublisher verificationMessagePublisher;

    @Autowired
    ThirdPartyVerificationService tpvService;

    @Override
    public void execute(WorkflowContext workflowContext) {
        if (workflowContext.getVerification() != null) {
            Verification verification = workflowContext.getVerification();
            if (VerificationWorklowUtil.isVerificationFailedOrPending(verification)) {
                tpvService.simulateVerification(verification);
                verification.setVerificationWorkFlowState(VerificationWorkFlowState.PENDING_VERIFICATION);
                repository.save(verification);
                verificationMessagePublisher.publish(verification,workflowContext.getRequestId());

            }
        }

    }


}
