package com.idrive.driveronboardingservice.worklow;

import com.idrive.driveronboardingservice.worklow.model.WorkflowContext;
import com.idrive.driveronboardingservice.worklow.step.WorkflowStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;

@Service
public class WorkflowExecutor {
    @Autowired
    WorkflowContext workflowContext;

    @Autowired
    List<WorkflowStep> workflowSteps;

    @PostConstruct
    private void init() {
        Collections.sort(workflowSteps, AnnotationAwareOrderComparator.INSTANCE);
    }

    public void execute() {
        workflowSteps.forEach(workflowStep -> workflowStep.execute(workflowContext));
    }
}
