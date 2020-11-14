package com.iucse.passnet.recruitment.adapter.controllers;

import com.iucse.passnet.recruitment.adapter.channel.CommandGateway;
import com.iucse.passnet.recruitment.adapter.forms.JobCreationForm;
import com.iucse.passnet.recruitment.usecase.commands.requests.BaseCommand;
import com.iucse.passnet.recruitment.usecase.commands.requests.TeacherAcceptStudentJobApplicationCommand;
import com.iucse.passnet.recruitment.usecase.commands.requests.TeacherPostJobCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecruiterController {
    private final CommandGateway commandGateway;

    @Autowired
    public RecruiterController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public void postJob(JobCreationForm form, String teacherId) {
        BaseCommand command = TeacherPostJobCommand.builder()
           .jobOwnerId(teacherId)
           .content(form.getContent())
           .jobName(form.getJobTitle())
           .courseName(form.getCourseName())
           .requirement(form.getRequirement())
           .semester(form.getSemester())
           .build();
        this.commandGateway.send(command);
    }

    public void acceptJobApplication(String jobApplicationId, String jobId) {
        BaseCommand command = TeacherAcceptStudentJobApplicationCommand.builder()
           .jobApplicationId(jobApplicationId)
           .jobId(jobId)
           .build();
        this.commandGateway.send(command);
    }
}
