package com.iucse.passnet.recruitment.adapter.controllers;

import com.iucse.passnet.recruitment.adapter.channel.CommandGateway;
import com.iucse.passnet.recruitment.adapter.forms.JobApplicationForm;
import com.iucse.passnet.recruitment.usecase.commands.requests.BaseCommand;
import com.iucse.passnet.recruitment.usecase.commands.requests.StudentApplyJobCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j(topic = "[ApplicatorController]")
public class ApplicatorController {

    private final CommandGateway commandGateway;

    @Autowired
    public ApplicatorController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public void studentApplyJob(JobApplicationForm formString, String studentId, String jobId){
        BaseCommand command = StudentApplyJobCommand.builder()
           .jobId(jobId)
           .studentId(studentId)
           .build();

        this.commandGateway.send(command);
    }
}
