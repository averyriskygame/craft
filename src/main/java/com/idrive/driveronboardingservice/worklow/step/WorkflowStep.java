package com.idrive.driveronboardingservice.worklow.step;

import com.idrive.driveronboardingservice.worklow.model.WorkflowContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;


public interface WorkflowStep {

     void execute( WorkflowContext workflowContext);

}
