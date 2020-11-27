package com.iucse.passnet.recruitment.usecase.commands.handlers;

import com.iucse.passnet.recruitment.adapter.channel.DomainEventBus;
import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.aggregate.job.entities.JobApplication;
import com.iucse.passnet.recruitment.domain.aggregate.job.vos.*;
import com.iucse.passnet.recruitment.domain.annotation.ApplyDomainEvent;
import com.iucse.passnet.recruitment.domain.repositories.JobAggregateRepository;
import com.iucse.passnet.recruitment.usecase.commands.requests.StudentApplyJobCommand;
import com.iucse.passnet.recruitment.usecase.events.events.DomainEvent;
import com.iucse.passnet.recruitment.usecase.events.events.EventTypes;
import com.iucse.passnet.recruitment.usecase.services.UUIDGeneratorService;
import lombok.Builder;

public class StudentApplyJobCommandHandler extends AbstractJobAggregateCommandHandler<Job> {

    private final StudentApplyJobCommand command;
    private final UUIDGeneratorService uuidGeneratorService;

    @Builder
    public StudentApplyJobCommandHandler(JobAggregateRepository aggregateRepository, DomainEventBus eventBus, StudentApplyJobCommand command, UUIDGeneratorService uuidGeneratorService) {
        super(aggregateRepository, eventBus);
        this.command = command;
        this.uuidGeneratorService = uuidGeneratorService;
    }

    @Override
    @ApplyDomainEvent(EventTypes.StudentAppliedJob)
    public Job execute() {
        Job job = this.aggregateRepository.findByIdWithJobApplications(new JobId(command.getJobId()));

        JobApplication newJobApplication = JobApplication.builder()
           .id(new JobApplicationId(this.uuidGeneratorService.generate().toString()))
           .applicationOwner(new UserId(command.getStudentId()))
           .letter(new ApplicationLetter(command.getLetter()))
           .content(new Content(command.getContent()))
           .applicationState(new ApplicationState(ApplicationStates.PENDING))
           .build();

        job.receiveJobApplication(newJobApplication);
        return this.aggregateRepository.save(job);
    }
}
