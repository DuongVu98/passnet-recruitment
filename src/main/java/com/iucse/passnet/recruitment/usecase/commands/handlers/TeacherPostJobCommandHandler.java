package com.iucse.passnet.recruitment.usecase.commands.handlers;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.aggregate.job.vos.*;
import com.iucse.passnet.recruitment.domain.repositories.TestRepository;
import com.iucse.passnet.recruitment.usecase.commands.requests.TeacherPostJobCommand;
import lombok.Builder;

public class TeacherPostJobCommandHandler extends AbstractJobAggregateCommandHandler<Job> {

    private final TeacherPostJobCommand command;

    @Builder
    public TeacherPostJobCommandHandler(TestRepository aggregateRepository, TeacherPostJobCommand command) {
        super(aggregateRepository);
        this.command = command;
    }

    @Override
    public Job execute() {
        Job newJob = Job.builder()
           .id(new JobId(""))
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
