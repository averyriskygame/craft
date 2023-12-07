package com.idrive.driveronboardingservice.worklow.step.impl;

import com.idrive.driveronboardingservice.model.Verification;
import com.idrive.driveronboardingservice.model.type.VerificationStatus;
import com.idrive.driveronboardingservice.model.type.VerificationWorkFlowState;
import com.idrive.driveronboardingservice.service.ThirdPartyVerificationService;
import com.idrive.driveronboardingservice.worklow.VerificationMessagePublisher;
import com.idrive.driveronboardingservice.worklow.model.WorkflowContext;
import com.idrive.driveronboardingservice.worklow.step.utill.VerificationWorklowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class CheckVerificationStatusStep extends WorkFlowStepImpl {

    @Autowired
    VerificationMessagePublisher verificationMessagePublisher;

    @Autowired
    ThirdPartyVerificationService tpvService;


    @Override
    public void execute(WorkflowContext workflowContext) {
        if (workflowContext.getVerification() != null) {
            Verification verification = workflowContext.getVerification();
            if (VerificationWorklowUtil.isVerificationInProgress(verification)) {

                //call third party to check verification status, if completed set complete
                // otherwise failed. Need to check on this.
                String tpvStatus = tpvService.checkStatus(verification);
                if (tpvStatus != null) {
                    verification.setVerificationWorkFlowState(VerificationWorkFlowState.VERIFICATION_COMPLETE);

                }
                if (tpvStatus.equals("SUCCESS")) {
                    verification.setVerifcationStatus(VerificationStatus.COMPLETE);
                } else {
                    verification.setVerifcationStatus(VerificationStatus.FAILED);
                    verificationMessagePublisher.publish(verification, workflowContext.getRequestId());
                }
                repository.save(verification);
            }
        }

    }


}
