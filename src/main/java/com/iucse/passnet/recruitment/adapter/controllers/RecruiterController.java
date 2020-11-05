package com.iucse.passnet.recruitment.adapter.controllers;

import com.iucse.passnet.recruitment.adapter.channel.DomainEventBus;
import com.iucse.passnet.recruitment.adapter.channel.EventBus;
import com.iucse.passnet.recruitment.adapter.grpc.CommandGateway;
import com.iucse.passnet.recruitment.domain.dto.Job;
import com.iucse.passnet.recruitment.domain.events.Event;
import com.iucse.passnet.recruitment.usecase.interactors.InteractorFactory;
import com.iucse.passnet.recruitment.usecase.interactors.commands.ActionCommand;
import com.iucse.passnet.recruitment.usecase.task.CommandActionTaskRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecruiterController {

    private final InteractorFactory interactorFactory;
    private final CommandGateway commandGateway;
    private final EventBus<Event> domainEventBus;

    public RecruiterController(InteractorFactory interactorFactory, CommandGateway commandGateway, @Qualifier("domain-event") EventBus<Event> domainEventBus) {
        this.interactorFactory = interactorFactory;
        this.commandGateway = commandGateway;
        this.domainEventBus = domainEventBus;
    }

    @Autowired


    public List<Job> getAllPostedJobs() {
        this.commandGateway.sendRequest();
        return null;
    }

    public void postJob(String teacherId, Job newJob) {
        ActionCommand command = interactorFactory.getTeacherPostJobCommand(teacherId, newJob);
        new Thread(new CommandActionTaskRunner(command)).start();
//        command.execute();
    }

    public void acceptApplicants(String studentId, String jobId) {
        ActionCommand command = interactorFactory.getTeacherAcceptStudentApplicationCommand(studentId, jobId);
        new Thread(new CommandActionTaskRunner(command)).start();
//        command.execute();
    }
}
