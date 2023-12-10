package com.idrive.driveronboardingservice.controller;

import com.idrive.driveronboardingservice.dto.UserDTO;
import com.idrive.driveronboardingservice.framework.HttpRequestContext;
import com.idrive.driveronboardingservice.model.User;
import com.idrive.driveronboardingservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/user")
@Slf4j
class UserController {

    @Autowired
    private HttpRequestContext httpRequestContext;

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") String id) throws Exception {
        log.info("api=GetUser requestId={} userId={}", httpRequestContext.getRequestId(), httpRequestContext.getUserId());

        UserDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);

    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid UserDTO user) throws Exception {
        log.info("api=CreateUser requestId={} userId={}", httpRequestContext.getRequestId(), httpRequestContext.getUserId());
        User userResponse = userService.saveUser(user);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }
}