package com.idrive.driveronboardingservice.service;

import com.idrive.driveronboardingservice.model.Verification;
import org.joda.time.Instant;
import org.springframework.stereotype.Service;

@Service
public class ThirdPartyVerificationService {

    public String checkStatus(Verification verification) {
        Instant currentInstant = Instant.now();
        if (currentInstant.hashCode() % 2 != 0) {
            return "FAILED";
        } else {
            return "SUCCESS";
        }
    }

    public void simulateVerification(Verification verification) {

    }
}
