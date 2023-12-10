package com.idrive.driveronboardingservice.worklow.step.utill;

import com.idrive.driveronboardingservice.model.Verification;
import com.idrive.driveronboardingservice.model.type.VerificationStatus;
import com.idrive.driveronboardingservice.model.type.VerificationWorkFlowState;

public class VerificationWorklowUtil {

    public static boolean isVerificationFailedOrPending(Verification verification) {
        VerificationStatus verificationStatus = verification.getVerificationStatus();
        VerificationWorkFlowState verificationWorkFlowState = verification.getVerificationWorkFlowState();

        if (verificationWorkFlowState.equals(VerificationWorkFlowState.DOCUMENT_COLLECTED)) {
            if (verificationStatus.equals(VerificationStatus.IN_PROGRESS)) {
                return true;
            }
        } else if (verificationWorkFlowState.equals(VerificationWorkFlowState.VERIFICATION_COMPLETE)
                && verificationStatus.equals(VerificationStatus.FAILED)) {
            return true;
        }
        return
                false;
    }

    public static boolean isVerificationInProgress(Verification verification) {
        VerificationStatus verificationStatus = verification.getVerificationStatus();
        VerificationWorkFlowState verificationWorkFlowState = verification.getVerificationWorkFlowState();

        if (verificationStatus.equals(VerificationStatus.IN_PROGRESS)
                && verificationWorkFlowState.equals(VerificationWorkFlowState.PENDING_VERIFICATION)) {
            return true;
        } else
            return false;
    }
}
