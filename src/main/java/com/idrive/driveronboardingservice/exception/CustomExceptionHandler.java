package com.idrive.driveronboardingservice.exception;

import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException exception, HttpServletRequest request) {
        var guid = UUID.randomUUID().toString();
        log.error(String.format("Error GUID=%s; error message: %s", guid, exception.getMessage()), exception);
        var response = new ErrorResponse(guid, exception.getErrorCode(), exception.getMessage(), exception.getHttpStatus().value(), exception.getHttpStatus().name(), request.getRequestURI(), request.getMethod(), LocalDateTime.now().now());
        return new ResponseEntity<>(response, exception.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleUnknownException(final Exception exception, final HttpServletRequest request) {
        var guid = UUID.randomUUID().toString();
        log.error(String.format("Error GUID=%s; error message: %s", guid, exception.getMessage()), exception);
        var response = new ErrorResponse(guid, "INTERNAL ERROR", "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(), request.getRequestURI(), request.getMethod(), LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(final MethodArgumentNotValidException ex, final HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        var guid = UUID.randomUUID().toString();
        log.error(String.format("Error GUID=%s; error message: %s", guid, ex.getMessage()), ex);
        var response = new ErrorResponse(guid, "Bad Request", errors.toString(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), request.getRequestURI(), request.getMethod(), LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}