package com.iucse.passnet.recruitment.usecase.commands;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.annotation.PrepareCommandHandler;
import com.iucse.passnet.recruitment.domain.annotation.PrepareDomainEvent;
import com.iucse.passnet.recruitment.usecase.commands.handlers.AbstractJobAggregateCommandHandler;
import com.iucse.passnet.recruitment.usecase.commands.handlers.StudentApplyJobCommandHandler;
import com.iucse.passnet.recruitment.usecase.commands.handlers.TeacherAcceptStudentApplicationCommandHandler;
import com.iucse.passnet.recruitment.usecase.commands.handlers.TeacherPostJobCommandHandler;
import com.iucse.passnet.recruitment.usecase.commands.requests.StudentApplyJobCommand;
import com.iucse.passnet.recruitment.usecase.commands.requests.TeacherAcceptStudentJobApplicationCommand;
import com.iucse.passnet.recruitment.usecase.commands.requests.TeacherPostJobCommand;
import com.iucse.passnet.recruitment.usecase.events.events.EventTypes;
import com.iucse.passnet.recruitment.usecase.services.UUIDGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandHandlerFactory {
    private final UUIDGeneratorService uuidGeneratorService;

    @Autowired
    public CommandHandlerFactory(UUIDGeneratorService uuidGeneratorService) {
        this.uuidGeneratorService = uuidGeneratorService;
    }

    @PrepareCommandHandler
    @PrepareDomainEvent(EventTypes.TeacherPostedJob)
    public AbstractJobAggregateCommandHandler<Job> getTeacherPostJobCommandHandler(TeacherPostJobCommand command) {
        return TeacherPostJobCommandHandler.builder()
                .command(command)
                .uuidGeneratorService(this.uuidGeneratorService)
                .build();
    }

    @PrepareCommandHandler
    @PrepareDomainEvent(EventTypes.StudentAppliedJob)
    public AbstractJobAggregateCommandHandler<Job> getStudentApplyJobCommandHandler(StudentApplyJobCommand command) {
        return StudentApplyJobCommandHandler.builder()
                .uuidGeneratorService(this.uuidGeneratorService)
                .command(command)
                .build();
    }

    @PrepareCommandHandler
    public AbstractJobAggregateCommandHandler<Job> getTeacherAcceptStudentJobApplicationCommandHandler(TeacherAcceptStudentJobApplicationCommand command) {
        return TeacherAcceptStudentApplicationCommandHandler.builder()
                .command(command)
                .build();
    }
}
