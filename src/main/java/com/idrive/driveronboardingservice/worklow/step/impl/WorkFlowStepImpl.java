package com.idrive.driveronboardingservice.worklow.step.impl;

import com.idrive.driveronboardingservice.repository.VerificationRepository;
import com.idrive.driveronboardingservice.worklow.step.WorkflowStep;
import org.springframework.beans.factory.annotation.Autowired;

public  abstract class WorkFlowStepImpl implements WorkflowStep {
    @Autowired
    VerificationRepository repository;



}
