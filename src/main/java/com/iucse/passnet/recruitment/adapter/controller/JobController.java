package com.iucse.passnet.recruitment.adapter.controller;

import com.iucse.passnet.recruitment.adapter.gateway.CommandGateway;
import com.iucse.passnet.recruitment.domain.forms.JobApplicationForm;
import com.iucse.passnet.recruitment.domain.forms.JobCreationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/jobs")
public class JobController extends BaseController {

    private final CommandGateway commandGateway;

    @Autowired
    public JobController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping(value = "/post-job")
    public ResponseEntity<Object> postNewJob(@Valid @RequestBody JobCreationForm form, @RequestParam("teacherId") String teacherId) {
        commandGateway.postJob(form, teacherId);
        return ok();
    }

    @PostMapping(value = "/{id}/apply-job")
    public ResponseEntity<Object> studentApplyJob(@PathVariable("id") String jobId, @Valid @RequestBody JobApplicationForm form, @RequestParam("studentId") String studentId) {
        commandGateway.studentApplyJob(form, studentId, jobId);
        return ok();
    }

    @PutMapping(value = "/{id}/accept-application")
    public ResponseEntity<Object> acceptJobApplication(@PathVariable("id") String jobId, @RequestParam("jobApplicationId") String jobApplicationId) {
        commandGateway.acceptJobApplication(jobApplicationId, jobId);
        return ok();
    }

    @PutMapping(value = "/{id}/remove-application")
    public ResponseEntity<Object> removeApplication(@PathVariable("id") String jobId, @RequestParam("jobApplicationId") String jobApplicationId) {
        commandGateway.removeJobApplication(jobApplicationId, jobId);
        return ok();
    }

    @DeleteMapping(value = "/{id}/delete-job")
    public ResponseEntity<Object> deleteJob(@PathVariable("id") String jobId) {
        commandGateway.deleteJob(jobId);
        return ok();
    }
}
