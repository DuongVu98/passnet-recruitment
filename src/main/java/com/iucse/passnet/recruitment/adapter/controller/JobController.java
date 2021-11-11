package com.iucse.passnet.recruitment.adapter.controller;

import com.iucse.passnet.recruitment.adapter.gateway.CommandGateway;
import com.iucse.passnet.recruitment.domain.commands.*;
import com.iucse.passnet.recruitment.domain.forms.JobApplicationForm;
import com.iucse.passnet.recruitment.domain.forms.JobCreationForm;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
@RequestMapping(value = "/jobs")
public class JobController extends BaseController {

    private CommandGateway commandGateway;

    @PostMapping(value = "/post-job")
    public ResponseEntity<Object> postNewJob(@Valid @RequestBody JobCreationForm form, @RequestParam("teacherId") String teacherId) {

        PostJobCommand command = PostJobCommand.builder()
           .jobOwnerId(teacherId)
           .content(form.getContent())
           .jobName(form.getJobTitle())
           .courseName(form.getCourseName())
           .requirement(form.getRequirement())
           .semester(form.getSemester())
           .organizationId(form.getOrganizationId())
           .build();

        commandGateway.sendCommand(command);
        return ok();
    }

    @PostMapping(value = "/{id}/apply-job")
    public ResponseEntity<Object> studentApplyJob(@PathVariable("id") String jobId, @Valid @RequestBody JobApplicationForm jobApplicationForm, @RequestParam("studentId") String studentId) {

        ApplyJobCommand command = ApplyJobCommand
           .builder()
           .jobId(jobId)
           .studentId(studentId)
           .content(jobApplicationForm.getContent())
           .letter(jobApplicationForm.getLetter())
           .build();

        commandGateway.sendCommand(command);
        return ok();
    }

    @PutMapping(value = "/{id}/accept-application")
    public ResponseEntity<Object> acceptJobApplication(@PathVariable("id") String jobId, @RequestParam("jobApplicationId") String jobApplicationId) {

        AcceptJobApplicationCommand command = AcceptJobApplicationCommand.builder().jobApplicationId(jobApplicationId).jobId(jobId).build();

        commandGateway.sendCommand(command);
        return ok();
    }

    @PutMapping(value = "/{id}/remove-application")
    public ResponseEntity<Object> removeApplication(@PathVariable("id") String jobId, @RequestParam("jobApplicationId") String jobApplicationId) {

        RemoveJobApplicationCommand command = RemoveJobApplicationCommand.builder().jobApplicationId(jobApplicationId).jobId(jobId).build();

        commandGateway.sendCommand(command);
        return ok();
    }

    @DeleteMapping(value = "/{id}/delete-job")
    public ResponseEntity<Object> deleteJob(@PathVariable("id") String jobId) {

        DeleteJobCommand command = DeleteJobCommand.builder().jobId(jobId).build();

        commandGateway.sendCommand(command);
        return ok();
    }
}
