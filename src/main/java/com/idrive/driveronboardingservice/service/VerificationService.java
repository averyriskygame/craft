package com.idrive.driveronboardingservice.service;

import com.idrive.driveronboardingservice.model.User;
import com.idrive.driveronboardingservice.model.Verification;
import com.idrive.driveronboardingservice.repository.UserRepository;
import com.idrive.driveronboardingservice.repository.VerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VerificationService {

    @Autowired
    VerificationRepository verificationRepository;


    public Verification getVerification(String userId) throws Exception {
       Optional<Verification> verificationOptional= verificationRepository.findByUserId(userId);
       if(verificationOptional.isPresent())
           return verificationOptional.get();
        throw new Exception("Cant find any user under given ID");
    }

    public Verification saveVerfication(Verification verification) {
        Verification verificationSaved = verificationRepository.save(verification);
        return verificationSaved;
    }

}
