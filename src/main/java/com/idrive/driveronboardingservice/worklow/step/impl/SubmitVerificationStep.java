package com.idrive.driveronboardingservice.worklow.step.impl;

import com.idrive.driveronboardingservice.model.Verification;
import com.idrive.driveronboardingservice.model.type.VerificationStatus;
import com.idrive.driveronboardingservice.model.type.VerificationWorkFlowState;
import com.idrive.driveronboardingservice.worklow.model.WorkflowContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class SubmitVerificationStep extends WorkFlowStepImpl {


    @Override
    public void execute(WorkflowContext workflowContext) {
        if (workflowContext.getVerification() != null) {
            Verification verification = workflowContext.getVerification();
            if (verification.getVerificationStatus().equals(VerificationStatus.IN_PROGRESS)
                    && verification.getVerificationWorkFlowState().equals(VerificationWorkFlowState.INITIATED)) {
                verification.setVerificationWorkFlowState(VerificationWorkFlowState.DOCUMENT_COLLECTED);
                repository.save(verification);

            }
        }
    }
}
