package com.iucse.passnet.recruitment.usecase.commands.handlers;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.aggregate.job.entities.JobApplication;
import com.iucse.passnet.recruitment.domain.aggregate.job.vos.*;
import com.iucse.passnet.recruitment.domain.repositories.JobAggregateRepository;
import com.iucse.passnet.recruitment.usecase.commands.requests.StudentApplyJobCommand;
import com.iucse.passnet.recruitment.usecase.services.UUIDGeneratorService;
import lombok.Builder;

public class StudentApplyJobCommandHandler extends AbstractJobAggregateCommandHandler<Job> {

    private final StudentApplyJobCommand command;
    private final UUIDGeneratorService uuidGeneratorService;

    @Builder
    public StudentApplyJobCommandHandler(JobAggregateRepository aggregateRepository, StudentApplyJobCommand command, UUIDGeneratorService uuidGeneratorService) {
        super(aggregateRepository);
        this.command = command;
        this.uuidGeneratorService = uuidGeneratorService;
    }

    @Override
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
