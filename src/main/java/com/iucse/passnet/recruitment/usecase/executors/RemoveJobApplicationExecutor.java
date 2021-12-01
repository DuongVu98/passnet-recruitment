package com.iucse.passnet.recruitment.usecase.executors;

import com.iucse.passnet.recruitment.domain.aggregate.entities.Job;
import com.iucse.passnet.recruitment.domain.aggregate.entities.JobApplication;
import com.iucse.passnet.recruitment.domain.aggregate.vos.JobApplicationId;
import com.iucse.passnet.recruitment.domain.aggregate.vos.JobId;
import com.iucse.passnet.recruitment.domain.commands.BaseCommand;
import com.iucse.passnet.recruitment.domain.commands.RemoveJobApplicationCommand;
import com.iucse.passnet.recruitment.domain.compensating.CompensatingCommand;
import com.iucse.passnet.recruitment.domain.compensating.RemoveJobApplicationCompensating;
import com.iucse.passnet.recruitment.domain.exceptions.JobApplicationNotFound;
import com.iucse.passnet.recruitment.domain.exceptions.WrongCommandTypeException;
import com.iucse.passnet.recruitment.domain.repositories.JobAggregateRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;


@Builder
@RequiredArgsConstructor
@Slf4j(topic = "[RemoveJobApplicationCommandExecutor]")
public class RemoveJobApplicationExecutor implements CommandExecutor, CompensatingHandler {
    private final JobAggregateRepository jobRepository;

    @Override
    public Job execute(BaseCommand baseCommand) {
        if (baseCommand instanceof RemoveJobApplicationCommand) {
            RemoveJobApplicationCommand command = (RemoveJobApplicationCommand) baseCommand;
            Job jobAggregate = this.jobRepository.findByIdWithJobApplications(new JobId(command.getJobId()));

            jobAggregate
               .getJobApplications()
               .stream()
               .filter(jobApplication -> jobApplication.getId().equals(new JobApplicationId(command.getJobApplicationId())))
               .findAny()
               .ifPresentOrElse(
                  jobAggregate::removeJobApplication,
                  () -> {
                      throw new JobApplicationNotFound("application doesn't exit in job");
                  }
               );

            return this.jobRepository.save(jobAggregate);

        } else {
            throw new WrongCommandTypeException("command must be TeacherRemoveStudentJobApplicationCommand");
        }
    }

    @Override
    public void reverse(CompensatingCommand compensatingCommand) {
        if (compensatingCommand instanceof RemoveJobApplicationCompensating) {
            RemoveJobApplicationCompensating command = (RemoveJobApplicationCompensating) compensatingCommand;
            Job jobAggregate = this.jobRepository.findByIdWithJobApplications(new JobId(command.getJobId()));

            Optional<JobApplication> optional = jobAggregate
               .getJobApplications()
               .stream()
               .filter(jobApplication -> jobApplication.getId().equals(new JobApplicationId(command.getJobApplicationId())))
               .findAny();

            if (optional.isPresent()) {
                JobApplication jobApplication = optional.get();
                jobAggregate.acceptJobApplication(jobApplication);

                Job updatedJob = this.jobRepository.save(jobAggregate);
            }
        }
    }
}
