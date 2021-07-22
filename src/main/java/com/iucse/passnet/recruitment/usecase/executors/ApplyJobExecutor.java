package com.iucse.passnet.recruitment.usecase.executors;

import com.iucse.passnet.recruitment.domain.aggregate.entities.Job;
import com.iucse.passnet.recruitment.domain.aggregate.entities.JobApplication;
import com.iucse.passnet.recruitment.domain.aggregate.vos.*;
import com.iucse.passnet.recruitment.domain.commands.BaseCommand;
import com.iucse.passnet.recruitment.domain.commands.StudentApplyJobCommand;
import com.iucse.passnet.recruitment.domain.exceptions.WrongCommandTypeException;
import com.iucse.passnet.recruitment.domain.repositories.JobAggregateRepository;
import com.iucse.passnet.recruitment.usecase.services.UUIDGeneratorService;
import lombok.Builder;

import javax.transaction.Transactional;

public class ApplyJobExecutor implements CommandExecutor {
    private final JobAggregateRepository jobRepository;
    private final UUIDGeneratorService uuidGeneratorService;

    @Builder
    public ApplyJobExecutor(JobAggregateRepository jobRepository, UUIDGeneratorService uuidGeneratorService) {
        this.jobRepository = jobRepository;
        this.uuidGeneratorService = uuidGeneratorService;
    }

    @Override
    @Transactional
    public Job execute(BaseCommand command) {
        if (command instanceof StudentApplyJobCommand) {
            StudentApplyJobCommand studentApplyJobCommand = (StudentApplyJobCommand) command;

            Job job = this.jobRepository.findByIdWithJobApplications(new JobId(studentApplyJobCommand.getJobId()));

            JobApplication newJobApplication = JobApplication
               .builder()
               .id(new JobApplicationId(this.uuidGeneratorService.generate().toString()))
               .applicationOwner(new ProfileId(studentApplyJobCommand.getStudentId()))
               .letter(new ApplicationLetter(studentApplyJobCommand.getLetter()))
               .content(new Content(studentApplyJobCommand.getContent()))
               .applicationState(ApplicationStates.PENDING)
               .build();

            job.receiveJobApplication(newJobApplication);
            return jobRepository.save(job);
        } else {
            throw new WrongCommandTypeException("command must be StudentApplicationCommand");
        }
    }
}
