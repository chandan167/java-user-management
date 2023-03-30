package com.chandansingh16794.exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        ExceptionResponse<Map<String, String>> response = new ExceptionResponse<>();
        response.setDateTime(LocalDateTime.now());
        response.setStatus(status.value());
        response.setErrorCode("VALIDATION_ERROR");
        Map<String, String> customErrors = new HashMap<>();
        errors.forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            customErrors.put(fieldName, message);
        });
        response.setValidationError(customErrors);
        return new ResponseEntity<>(response, status);
    }


    @ExceptionHandler(HttpException.class)
    protected ResponseEntity<Object> handleHttpException(HttpException exception, WebRequest webRequest){
        ExceptionResponse<Object> response = new ExceptionResponse<>();
        response.setDateTime(LocalDateTime.now());
        response.setStatus(exception.getStatus().value());
        response.setErrorCode(exception.getErrorCode());
        response.setMessage(exception.getMessage());
        return  new ResponseEntity<>(response, exception.getStatus());
    }


    protected @ExceptionHandler(Exception.class)
    ResponseEntity<Object> handleGlobalException(Exception exception, WebRequest webRequest){
        ExceptionResponse<Object> response = new ExceptionResponse<>();
        response.setDateTime(LocalDateTime.now());
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setErrorCode("INTERNAL_SERVER_ERROR");
        response.setMessage(exception.getMessage());
        return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
