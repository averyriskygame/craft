package com.idrive.driveronboardingservice.controller;

import com.idrive.driveronboardingservice.framework.HttpRequestContext;
import com.idrive.driveronboardingservice.model.Verification;
import com.idrive.driveronboardingservice.model.VerificationMessage;
import com.idrive.driveronboardingservice.queue.VerificationMessageProducer;
import com.idrive.driveronboardingservice.service.VerificationService;
import com.idrive.driveronboardingservice.worklow.WorkflowExecutor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.message.Message;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
@Slf4j
public class VerificationController {

    @Autowired
    private HttpRequestContext httpRequestContext;

    @Autowired
    private VerificationService verificationService;

    @Autowired
    VerificationMessageProducer producer;
    @Autowired
    WorkflowExecutor workflowExecutor;

    @GetMapping("/{id}/verification")
    public ResponseEntity getVerification(@PathVariable("id") String userId) throws Exception {
        log.info("api=GetVerification requestId={} userId={}", httpRequestContext.getRequestId(), httpRequestContext.getUserId());
        Verification verification = verificationService.getVerification(userId);
        return ResponseEntity.ok(verification);

    }

    @PostMapping("/{id}/verification")
    public ResponseEntity requestVerification(@RequestBody Verification verification) throws Exception {
        log.info("api=CreateVerification requestId={} userId={}", httpRequestContext.getRequestId(), httpRequestContext.getUserId());
        Verification verificationResponse = verificationService.saveVerfication(verification);
        return new ResponseEntity<Verification>(verificationResponse, HttpStatus.CREATED);

    }

    @PostMapping("/{id}/verification/submit")
    public ResponseEntity submit (@RequestBody Verification verification) throws Exception {
        log.info("api=CreateVerification requestId={} userId={}", httpRequestContext.getRequestId(), httpRequestContext.getUserId());
        //workflowExecutor.execute();

        VerificationMessage message= new VerificationMessage();
        message.setVerificationId(verification.getVerificationId());
        message.setRequestId(httpRequestContext.getRequestId());
        producer.sendJsonMessage(message);
        return  ResponseEntity.accepted().build();
    }
}
