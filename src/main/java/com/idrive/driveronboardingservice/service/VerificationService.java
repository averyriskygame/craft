package com.idrive.driveronboardingservice.service;

import com.idrive.driveronboardingservice.dto.VerificationDTO;
import com.idrive.driveronboardingservice.exception.CustomException;
import com.idrive.driveronboardingservice.mapper.VerificationMapper;
import com.idrive.driveronboardingservice.model.Verification;
import com.idrive.driveronboardingservice.repository.VerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VerificationService {

    @Autowired
    VerificationRepository verificationRepository;


    public VerificationDTO getVerificationByUserId(String userId) throws Exception {
        Optional<Verification> verificationOptional = verificationRepository.findByUserId(userId);
        if (verificationOptional.isPresent()) {
            Verification verification = verificationOptional.get();
            return VerificationMapper.mapToVerificationDto(verification);
        }
        throw new CustomException("user-not-found", "User with id=%id not found", HttpStatus.NOT_FOUND);
    }

    public VerificationDTO getVerification(String verificationId) throws Exception {
        Optional<Verification> verificationOptional = verificationRepository.findById(verificationId);
        if (verificationOptional.isPresent()) {
            Verification verification = verificationOptional.get();
            return VerificationMapper.mapToVerificationDto(verification);
        }
        throw new CustomException("verification-not-found",
                "Verification with with id=%verificationId not found", HttpStatus.NOT_FOUND);
    }

    public Verification saveVerification(VerificationDTO verificationDTO) {
        Verification verification=VerificationMapper.mapToVerification(verificationDTO);
        return verificationRepository.save(verification);
    }

}
