package com.example.announcement_service.controller;

import com.example.announcement_service.model.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.stream.Collectors;

@ControllerAdvice
public class CommonControllerAdvice {
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError onConstraintValidationException(HttpServletRequest request, ConstraintViolationException e) {
        String message = e.getConstraintViolations().stream()
                .map(violation -> violation.getPropertyPath().toString() + ": " + violation.getMessage())
                .collect(Collectors.joining(", "));
        return ApiError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error("bad_request")
                .message(message)
                .path(String.valueOf(request.getRequestURI()))
                .build();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiError onMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() +": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return ApiError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error("bad_request")
                .message(message)
                .path(String.valueOf(request.getRequestURI()))
                .build();
    }
}
