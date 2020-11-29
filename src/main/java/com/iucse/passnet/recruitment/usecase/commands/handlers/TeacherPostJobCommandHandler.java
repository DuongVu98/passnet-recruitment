package com.iucse.passnet.recruitment.usecase.commands.handlers;

import com.iucse.passnet.recruitment.adapter.channel.DomainEventBus;
import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.aggregate.job.vos.*;
import com.iucse.passnet.recruitment.domain.repositories.JobAggregateRepository;
import com.iucse.passnet.recruitment.usecase.commands.requests.TeacherPostJobCommand;
import com.iucse.passnet.recruitment.usecase.events.events.DomainEvent;
import com.iucse.passnet.recruitment.usecase.events.events.EventTypes;
import com.iucse.passnet.recruitment.usecase.services.UUIDGeneratorService;
import lombok.Builder;

public class TeacherPostJobCommandHandler extends AbstractJobAggregateCommandHandler<Job> {
    private final UUIDGeneratorService uuidGeneratorService;
    private final TeacherPostJobCommand command;

    @Builder
    public TeacherPostJobCommandHandler(JobAggregateRepository aggregateRepository, EventTypes eventToApply, DomainEventBus eventBus, UUIDGeneratorService uuidGeneratorService, TeacherPostJobCommand command) {
        super(aggregateRepository, eventToApply, eventBus);
        this.uuidGeneratorService = uuidGeneratorService;
        this.command = command;
    }

    @Override
    public DomainEvent execute() {
        Job newJob = Job.builder()
           .id(new JobId(uuidGeneratorService.generate().toString()))
           .jobName(new JobName(command.getJobName()))
           .courseName(new CourseName(command.getCourseName()))
           .content(new Content(command.getContent()))
           .jobRequirement(new JobRequirement(command.getRequirement()))
           .semester(new Semester(command.getSemester()))
           .jobOwner(new UserId(command.getJobOwnerId()))
           .build();

        Job aggregate = this.aggregateRepository.save(newJob);
        return new DomainEvent(this.getEventToApply(), aggregate, aggregate.getId());
    }
}
