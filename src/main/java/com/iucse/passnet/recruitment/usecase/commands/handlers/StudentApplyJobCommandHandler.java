package com.iucse.passnet.recruitment.usecase.commands.handlers;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.aggregate.job.entities.JobApplication;
import com.iucse.passnet.recruitment.domain.aggregate.job.vos.*;
import com.iucse.passnet.recruitment.domain.repositories.TestRepository;
import com.iucse.passnet.recruitment.usecase.commands.requests.StudentApplyJobCommand;
import lombok.Builder;

public class StudentApplyJobCommandHandler extends AbstractJobAggregateCommandHandler<Job> {

    private final StudentApplyJobCommand command;

    @Builder
    public StudentApplyJobCommandHandler(TestRepository aggregateRepository, StudentApplyJobCommand command) {
        super(aggregateRepository);
        this.command = command;
    }

    @Override
    public Job execute() {
        Job job = this.aggregateRepository.findByIdWithJobApplications(new JobId(command.getJobId()));

        JobApplication newJobApplication = JobApplication.builder()
           .id(new JobApplicationId(""))
           .applicationOwner(new UserId(command.getStudentId()))
           .letter(new ApplicationLetter())
           .content(new Content())
           .applicationState(new ApplicationState(ApplicationStates.PENDING))
           .build();

        job.receiveJobApplication(newJobApplication);
        return this.aggregateRepository.save(job);
    }
}
