package com.idrive.driveronboardingservice.mapper;

import com.idrive.driveronboardingservice.dto.VerificationDTO;
import com.idrive.driveronboardingservice.model.Verification;

public class VerificationMapper {

    public static VerificationDTO mapToVerificationDto(Verification verification) {
        VerificationDTO verificationDTO = new VerificationDTO(verification.getVerificationId(),
                verification.getUserId(), verification.getDocId(), verification.getRetryCount(),
                verification.getVerificationStatus(), verification.getVerificationWorkFlowState(),
                verification.getVerificationType());
        return verificationDTO;
    }

    public static Verification mapToVerification(VerificationDTO verificationDTO) {
        Verification verification = new Verification(verificationDTO.getVerificationId(),
                verificationDTO.getUserId(), verificationDTO.getDocId(), verificationDTO.getRetryCount(),
                verificationDTO.getVerificationStatus(), verificationDTO.getVerificationWorkFlowState(),
                verificationDTO.getVerificationType());
        return verification;
    }
}
