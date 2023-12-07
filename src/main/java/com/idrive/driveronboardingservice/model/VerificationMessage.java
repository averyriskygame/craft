package com.idrive.driveronboardingservice.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class VerificationMessage {

    private String requestId;

    private String verificationId;

    public VerificationMessage(String requestId, String verificationId) {
        this.requestId = requestId;
        this.verificationId = verificationId;
    }
}
