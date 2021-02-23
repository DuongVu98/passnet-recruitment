package com.iucse.passnet.recruitment.usecase.test.executors;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.aggregate.job.entities.JobApplication;
import com.iucse.passnet.recruitment.domain.aggregate.job.vos.JobApplicationId;
import com.iucse.passnet.recruitment.domain.aggregate.job.vos.JobId;
import com.iucse.passnet.recruitment.domain.exceptions.JobApplicationNotFound;
import com.iucse.passnet.recruitment.domain.repositories.JobAggregateRepository;
import com.iucse.passnet.recruitment.usecase.events.events.DomainEvent;
import com.iucse.passnet.recruitment.usecase.test.commands.TeacherAcceptStudentJobApplicationCommand;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j(topic = "[TeacherAcceptStudentJobApplicationCommandExecutor]")
public class TeacherAcceptStudentJobApplicationCommandExecutor extends AbstractCommandExecutor<TeacherAcceptStudentJobApplicationCommand, Job>{

    @Builder
    public TeacherAcceptStudentJobApplicationCommandExecutor(JobAggregateRepository aggregateRepository) {
        super(aggregateRepository);
    }

    @Override
    protected Job execute(TeacherAcceptStudentJobApplicationCommand command) throws JobApplicationNotFound {
        Job jobAggregate = this.aggregateRepository.findByIdWithJobApplications(new JobId(command.getJobId()));
        Optional<JobApplication> optional = jobAggregate
           .getJobApplications()
           .stream()
           .filter(
              jobApplication -> jobApplication.getId().equal(new JobApplicationId(command.getJobApplicationId()))
           )
           .findAny();

        if (optional.isPresent()) {
            JobApplication jobApplication = optional.get();
            jobAggregate.acceptJobApplication(jobApplication);

            return this.aggregateRepository.save(jobAggregate);
        } else {
            throw new JobApplicationNotFound("application {} doesn't exit in job");
        }
    }
}
