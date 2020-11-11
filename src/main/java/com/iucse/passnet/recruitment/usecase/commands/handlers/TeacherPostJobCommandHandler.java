package com.iucse.passnet.recruitment.usecase.commands.handlers;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.aggregate.job.vos.*;
import com.iucse.passnet.recruitment.domain.repositories.JobAggregateRepository;
import com.iucse.passnet.recruitment.usecase.commands.requests.TeacherPostJobCommand;
import com.iucse.passnet.recruitment.usecase.services.UUIDGeneratorService;
import lombok.Builder;

public class TeacherPostJobCommandHandler extends AbstractJobAggregateCommandHandler<Job> {

    private final TeacherPostJobCommand command;
    private final UUIDGeneratorService uuidGeneratorService;

    @Builder
    public TeacherPostJobCommandHandler(JobAggregateRepository aggregateRepository, TeacherPostJobCommand command, UUIDGeneratorService uuidGeneratorService) {
        super(aggregateRepository);
        this.command = command;
        this.uuidGeneratorService = uuidGeneratorService;
    }

    @Override
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
