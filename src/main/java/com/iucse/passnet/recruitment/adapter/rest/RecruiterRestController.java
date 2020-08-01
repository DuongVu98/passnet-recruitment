package com.iucse.passnet.recruitment.adapter.rest;

import com.iucse.passnet.recruitment.adapter.controllers.RecruiterController;
import com.iucse.passnet.recruitment.domain.dto.Job;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/recruiter")
@Slf4j(topic = "[RecruiterRestController]")
public class RecruiterRestController {

    private final RecruiterController recruiterController;

    @Autowired
    public RecruiterRestController(RecruiterController recruiterController) {
        this.recruiterController = recruiterController;
    }

    @GetMapping(value = "/posted-jobs")
    public List<Job> getAllPostedJobs(){
        return this.recruiterController.getAllPostedJobs();
    }

    @PostMapping(value = "/post-job/{teacherId}")
    public void postJob(@PathVariable("teacherId") String teacherId){

        log.info("Teacher Id: {}", teacherId);
        // draft data
        Job job = Job.builder()
                .department("CSE")
                .title("Physics 4")
                .description("blah blah")
                .build();
        this.recruiterController.postJob(teacherId, job);
    }

    @GetMapping(value = "/accept")
    public void acceptApplicants(String studentId){
        this.recruiterController.acceptApplicants(studentId);
    }
}
