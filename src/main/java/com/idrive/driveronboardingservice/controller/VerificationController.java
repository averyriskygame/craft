package com.idrive.driveronboardingservice.controller;

import com.idrive.driveronboardingservice.dto.VerificationDTO;
import com.idrive.driveronboardingservice.framework.HttpRequestContext;
import com.idrive.driveronboardingservice.model.Verification;
import com.idrive.driveronboardingservice.queue.VerificationMessageProducer;
import com.idrive.driveronboardingservice.service.VerificationService;
import com.idrive.driveronboardingservice.worklow.WorkflowExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/user")
@Slf4j
public class VerificationController {

    @Autowired
    private HttpRequestContext httpRequestContext;

    @Autowired
    private VerificationService verificationService;

    @Autowired
    private VerificationMessageProducer producer;
    @Autowired
    private WorkflowExecutor workflowExecutor;

    @GetMapping("/{id}/verification")
    public ResponseEntity<VerificationDTO> getVerification(@PathVariable("id") String userId) throws Exception {
        log.info("api=GetVerification requestId={} userId={}", httpRequestContext.getRequestId(), httpRequestContext.getUserId());
        VerificationDTO verification = verificationService.getVerificationByUserId(userId);
        return ResponseEntity.ok(verification);

    }

    @PostMapping("/{id}/verification")
    public ResponseEntity requestVerification(@Valid @RequestBody VerificationDTO verification) throws Exception {
        log.info("api=RequestVerification requestId={} userId={}", httpRequestContext.getRequestId(), httpRequestContext.getUserId());
        Verification verificationResponse = verificationService.saveVerification(verification);
        return new ResponseEntity<Verification>(verificationResponse, HttpStatus.CREATED);

    }

    @PostMapping("/{id}/verification/submit")
    public ResponseEntity submitVerification(@RequestBody VerificationDTO verification) throws Exception {
        log.info("api=SubmitVerification requestId={} userId={}", httpRequestContext.getRequestId(), httpRequestContext.getUserId());
        workflowExecutor.execute(verification, httpRequestContext.getRequestId());
        return ResponseEntity.accepted().build();
    }
}
