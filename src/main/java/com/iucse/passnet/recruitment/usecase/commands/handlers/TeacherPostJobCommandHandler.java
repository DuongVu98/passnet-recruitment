package com.iucse.passnet.recruitment.usecase.commands.handlers;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.aggregate.job.vos.JobName;
import com.iucse.passnet.recruitment.domain.aggregate.job.vos.JobRequirement;
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
           .jobName(new JobName(command.getJobName()))
           .jobRequirement(new JobRequirement(command.getRequirement()))
           .build();

        return this.aggregateRepository.save(newJob);
    }
}
