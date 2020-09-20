package com.iucse.passnet.recruitment.adapter.rest;

import com.iucse.passnet.recruitment.domain.helpers.ErrorObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {
    protected ResponseEntity<?> notFound() {
        return new ResponseEntity<>(
           ErrorObject.builder()
              .errorCode(HttpStatus.NOT_FOUND.toString())
              .errorDescription("Not Found")
              .build(),
           HttpStatus.NOT_FOUND
        );
    }

    protected ResponseEntity<?> badRequest(Throwable throwable) {
        return new ResponseEntity<>(
           ErrorObject.builder()
              .errorCode(HttpStatus.BAD_REQUEST.toString())
              .errorDescription("Not Found")
              .build(),
           HttpStatus.BAD_REQUEST
        );
    }
}
