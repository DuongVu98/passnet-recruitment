package com.iucse.passnet.recruitment.usecase.commands;

import com.iucse.passnet.recruitment.adapter.channel.DomainEventBus;
import com.iucse.passnet.recruitment.adapter.channel.EventBus;
import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.annotation.PrepareCommandHandler;
import com.iucse.passnet.recruitment.domain.annotation.PrepareDomainEvent;
import com.iucse.passnet.recruitment.domain.repositories.JobAggregateRepository;
import com.iucse.passnet.recruitment.usecase.commands.handlers.AbstractJobAggregateCommandHandler;
import com.iucse.passnet.recruitment.usecase.commands.handlers.StudentApplyJobCommandHandler;
import com.iucse.passnet.recruitment.usecase.commands.handlers.TeacherAcceptStudentApplicationCommandHandler;
import com.iucse.passnet.recruitment.usecase.commands.handlers.TeacherPostJobCommandHandler;
import com.iucse.passnet.recruitment.usecase.commands.requests.BaseCommand;
import com.iucse.passnet.recruitment.usecase.commands.requests.StudentApplyJobCommand;
import com.iucse.passnet.recruitment.usecase.commands.requests.TeacherAcceptStudentJobApplicationCommand;
import com.iucse.passnet.recruitment.usecase.commands.requests.TeacherPostJobCommand;
import com.iucse.passnet.recruitment.usecase.events.events.DomainEvent;
import com.iucse.passnet.recruitment.usecase.events.events.EventTypes;
import com.iucse.passnet.recruitment.usecase.services.UUIDGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandHandlerFactory {

    private final JobAggregateRepository jobAggregateRepository;
    private final UUIDGeneratorService uuidGeneratorService;
    private final DomainEventBus eventBus;

    @Autowired
    public CommandHandlerFactory(JobAggregateRepository jobAggregateRepository, UUIDGeneratorService uuidGeneratorService, DomainEventBus eventBus) {
        this.jobAggregateRepository = jobAggregateRepository;
        this.uuidGeneratorService = uuidGeneratorService;
        this.eventBus = eventBus;
    }

//    public AbstractJobAggregateCommandHandler<Job> getJobAggregateCommandHandler(BaseCommand command) {
//        if (command instanceof TeacherPostJobCommand) {
//            return this.getTeacherPostJobCommandHandler((TeacherPostJobCommand) command);
//        } else if (command instanceof StudentApplyJobCommand) {
//            return this.getStudentApplyJobCommandHandler((StudentApplyJobCommand) command);
//        } else if (command instanceof TeacherAcceptStudentJobApplicationCommand) {
//            return this.getTeacherAcceptStudentJobApplicationCommandHandler((TeacherAcceptStudentJobApplicationCommand) command);
//        } else {
//            return null;
//        }
//    }

    @PrepareCommandHandler
    @PrepareDomainEvent(EventTypes.TeacherPostedJob)
    public AbstractJobAggregateCommandHandler<Job> getTeacherPostJobCommandHandler(TeacherPostJobCommand command) {
        return TeacherPostJobCommandHandler.builder()
                .command(command)
                .uuidGeneratorService(this.uuidGeneratorService)
//                .aggregateRepository(this.jobAggregateRepository)
//                .eventBus(this.eventBus)
                .build();
    }

    @PrepareCommandHandler
    @PrepareDomainEvent(EventTypes.StudentAppliedJob)
    public AbstractJobAggregateCommandHandler<Job> getStudentApplyJobCommandHandler(StudentApplyJobCommand command) {
        return StudentApplyJobCommandHandler.builder()
                .uuidGeneratorService(this.uuidGeneratorService)
                .command(command)
//                .eventBus(this.eventBus)
//                .aggregateRepository(this.jobAggregateRepository)
                .build();
    }

    @PrepareCommandHandler
    public AbstractJobAggregateCommandHandler<Job> getTeacherAcceptStudentJobApplicationCommandHandler(TeacherAcceptStudentJobApplicationCommand command) {
        return TeacherAcceptStudentApplicationCommandHandler.builder()
                .command(command)
//                .aggregateRepository(this.jobAggregateRepository)
//                .eventBus(this.eventBus)
                .build();
    }
}
