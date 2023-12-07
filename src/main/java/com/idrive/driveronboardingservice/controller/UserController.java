package com.idrive.driveronboardingservice.controller;

import com.idrive.driveronboardingservice.framework.HttpRequestContext;
import com.idrive.driveronboardingservice.model.User;
import com.idrive.driveronboardingservice.repository.UserRepository;
import com.idrive.driveronboardingservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/user")
@Slf4j
class UserController {

    @Autowired
    private HttpRequestContext httpRequestContext;

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable("id") String id) throws Exception {
        log.info("api=GetUser requestId={} userId={}", httpRequestContext.getRequestId(), httpRequestContext.getUserId());
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);

    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) throws Exception {
        log.info("api=CreateUser requestId={} userId={}", httpRequestContext.getRequestId(), httpRequestContext.getUserId());
        User userResponse = userService.saveUser(user);
        return new ResponseEntity<>(userResponse,HttpStatus.CREATED);
    }
}