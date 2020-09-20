package com.iucse.passnet.recruitment.adapter.rest;

import com.iucse.passnet.recruitment.domain.dto.Job;
import com.iucse.passnet.recruitment.domain.helpers.ErrorObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class HomeRestController {

    @GetMapping(value = "")
    public String getHomePage() {
        return "Hello";
    }

    @GetMapping(value = "/posted-jobs/{teacherId}")
    public List<Job> getAllPostedJobsFromTeacher(@PathVariable String teacherId) {
        return null;
    }

    protected ResponseEntity<?> notFound() {
        return new ResponseEntity<>(
           ErrorObject.builder()
              .errorCode(HttpStatus.NOT_FOUND.toString())
              .errorDescription("Not Found")
              .build(),
           HttpStatus.NOT_FOUND
        );
    }

    protected ResponseEntity<?> badRequest() {
        return new ResponseEntity<>(
           ErrorObject.builder()
              .errorCode(HttpStatus.BAD_REQUEST.toString())
              .errorDescription("Not Found")
              .build(),
           HttpStatus.BAD_REQUEST
        );
    }
}
