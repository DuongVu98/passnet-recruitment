package com.iucse.passnet.recruitment.adapter.rest;

import com.iucse.passnet.recruitment.domain.dto.Job;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class HomeRestController {

    @GetMapping(value = "")
    public String getHomePage(){
        return "Hello";
    }

    @GetMapping(value = "/posted-jobs/{teacherId}")
    public List<Job> getAllPostedJobsFromTeacher(@PathVariable String teacherId){
        return null;
    }
}
