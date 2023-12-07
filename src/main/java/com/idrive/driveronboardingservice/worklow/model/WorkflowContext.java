package com.idrive.driveronboardingservice.worklow.model;

import com.idrive.driveronboardingservice.model.Verification;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class WorkflowContext {

    @Autowired
    Verification verification;

    String requestId;

}
