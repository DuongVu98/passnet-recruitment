package com.iucse.passnet.recruitment.usecase.commands.handlers;

import com.iucse.passnet.recruitment.adapter.channel.DomainEventBus;
import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.aggregate.job.vos.*;
import com.iucse.passnet.recruitment.domain.annotation.ApplyDomainEvent;
import com.iucse.passnet.recruitment.domain.repositories.JobAggregateRepository;
import com.iucse.passnet.recruitment.usecase.commands.requests.TeacherPostJobCommand;
import com.iucse.passnet.recruitment.usecase.events.events.DomainEvent;
import com.iucse.passnet.recruitment.usecase.events.events.EventTypes;
import com.iucse.passnet.recruitment.usecase.services.UUIDGeneratorService;
import lombok.Builder;

public class TeacherPostJobCommandHandler extends AbstractJobAggregateCommandHandler<Job> {

    private final TeacherPostJobCommand command;
    private final UUIDGeneratorService uuidGeneratorService;

    @Builder
    public TeacherPostJobCommandHandler(JobAggregateRepository aggregateRepository, DomainEventBus domainEventBus, TeacherPostJobCommand command, UUIDGeneratorService uuidGeneratorService) {
        super(aggregateRepository, domainEventBus);
        this.command = command;
        this.uuidGeneratorService = uuidGeneratorService;
    }

    @Override
    @ApplyDomainEvent(EventTypes.TeacherPostedJob)
    public Job execute() {
        Job newJob = Job.builder()
           .id(new JobId(uuidGeneratorService.generate().toString()))
           .jobName(new JobName(command.getJobName()))
           .courseName(new CourseName(command.getCourseName()))
           .content(new Content(command.getContent()))
           .jobRequirement(new JobRequirement(command.getRequirement()))
           .semester(new Semester(command.getSemester()))
           .jobOwner(new UserId(command.getJobOwnerId()))
           .build();

        return this.aggregateRepository.save(newJob);
    }
}
