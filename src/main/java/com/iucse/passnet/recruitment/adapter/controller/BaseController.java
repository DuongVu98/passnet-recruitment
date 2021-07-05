package com.iucse.passnet.recruitment.adapter.controller;

import com.iucse.passnet.recruitment.domain.views.ErrorView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {

    protected ResponseEntity<ErrorView> notFound(String message) {
        return new ResponseEntity<>(new ErrorView(HttpStatus.NOT_FOUND.toString(), message), HttpStatus.NOT_FOUND);
    }

    protected ResponseEntity<ErrorView> badRequest(String message) {
        return new ResponseEntity<>(new ErrorView(HttpStatus.BAD_REQUEST.toString(), message), HttpStatus.BAD_REQUEST);
    }

    protected ResponseEntity<Object> ok() {
        return ResponseEntity.ok().build();
    }
}
