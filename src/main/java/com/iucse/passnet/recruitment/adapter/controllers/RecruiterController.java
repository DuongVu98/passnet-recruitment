package com.iucse.passnet.recruitment.adapter.controllers;

import com.iucse.passnet.recruitment.adapter.channel.CommandGateway;
import com.iucse.passnet.recruitment.adapter.channel.DomainEventBus;
import com.iucse.passnet.recruitment.adapter.channel.EventBus;
import com.iucse.passnet.recruitment.adapter.forms.JobCreationForm;
import com.iucse.passnet.recruitment.domain.aggregate.job.vos.UserId;
import com.iucse.passnet.recruitment.domain.dto.Job;
import com.iucse.passnet.recruitment.domain.events.Event;
import com.iucse.passnet.recruitment.usecase.commands.requests.BaseCommand;
import com.iucse.passnet.recruitment.usecase.commands.requests.TeacherPostJobCommand;
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
    private final EventBus<Event> domainEventBus;
    private final CommandGateway commandGateway;

    @Autowired
    public RecruiterController(InteractorFactory interactorFactory, EventBus<Event> domainEventBus, CommandGateway commandGateway) {
        this.interactorFactory = interactorFactory;
        this.domainEventBus = domainEventBus;
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

    public void acceptApplicants(String studentId, String jobId) {
        ActionCommand command = interactorFactory.getTeacherAcceptStudentApplicationCommand(studentId, jobId);
        new Thread(new CommandActionTaskRunner(command)).start();
//        command.execute();
    }
}
