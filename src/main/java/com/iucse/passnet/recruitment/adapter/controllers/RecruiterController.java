package com.iucse.passnet.recruitment.adapter.controllers;

import com.iucse.passnet.recruitment.adapter.grpc.CommandGateway;
import com.iucse.passnet.recruitment.domain.dto.Job;
import com.iucse.passnet.recruitment.usecase.interactors.InteractorFactory;
import com.iucse.passnet.recruitment.usecase.interactors.commands.ActionCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecruiterController {

    private final InteractorFactory interactorFactory;
    private final CommandGateway commandGateway;

    @Autowired
    public RecruiterController(InteractorFactory interactorFactory, CommandGateway commandGateway) {
        this.interactorFactory = interactorFactory;
        this.commandGateway = commandGateway;
    }

    public List<Job> getAllPostedJobs() {
        this.commandGateway.sendRequest();
        return null;
    }

    public void postJob(String teacherId, Job newJob) {
        ActionCommand command = interactorFactory.getTeacherPostJobCommand(teacherId, newJob);
        command.execute();
    }

    public void acceptApplicants(String studentId, String jobId) {
        ActionCommand command = interactorFactory.getTeacherAcceptStudentApplicationCommand(studentId, jobId);
        command.execute();
    }
}
