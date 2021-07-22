package com.iucse.passnet.recruitment.adapter.controller;

import com.iucse.passnet.recruitment.domain.exceptions.JobApplicationNotFound;
import com.iucse.passnet.recruitment.domain.exceptions.JobNotFoundException;
import com.iucse.passnet.recruitment.domain.exceptions.NullIdentifierException;
import com.iucse.passnet.recruitment.domain.views.ErrorView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends BaseController {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex
           .getBindingResult()
           .getAllErrors()
           .forEach(
              error -> {
                  String fieldName = ((FieldError) error).getField();
                  String errorMessage = error.getDefaultMessage();
                  errors.put(fieldName, errorMessage);
              }
           );

        return errors;
    }

    @ExceptionHandler(JobApplicationNotFound.class)
    public ResponseEntity<ErrorView> handle(JobApplicationNotFound exception) {
        return notFound(exception.getMessage());
    }

    @ExceptionHandler(JobNotFoundException.class)
    public ResponseEntity<ErrorView> handle(JobNotFoundException exception) {
        return notFound(exception.getMessage());
    }

    @ExceptionHandler(NullIdentifierException.class)
    public ResponseEntity<ErrorView> handle(NullIdentifierException exception) {
        return badRequest(exception.getMessage());
    }
}
