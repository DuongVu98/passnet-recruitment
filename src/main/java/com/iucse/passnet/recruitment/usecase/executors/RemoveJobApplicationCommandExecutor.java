package com.iucse.passnet.recruitment.usecase.executors;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.aggregate.job.entities.JobApplication;
import com.iucse.passnet.recruitment.domain.aggregate.job.vos.JobApplicationId;
import com.iucse.passnet.recruitment.domain.aggregate.job.vos.JobId;
import com.iucse.passnet.recruitment.domain.commands.BaseCommand;
import com.iucse.passnet.recruitment.domain.commands.RemoveJobApplicationCommand;
import com.iucse.passnet.recruitment.domain.compensating.CompensatingCommand;
import com.iucse.passnet.recruitment.domain.compensating.RemoveJobApplicationCompensating;
import com.iucse.passnet.recruitment.domain.exceptions.JobApplicationNotFound;
import com.iucse.passnet.recruitment.domain.exceptions.WrongCommandTypeException;
import com.iucse.passnet.recruitment.domain.repositories.JobAggregateRepository;
import java.util.Optional;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "[RemoveJobApplicationCommandExecutor]")
public class RemoveJobApplicationCommandExecutor implements CommandExecutor, CompensatingHandler {
	private final JobAggregateRepository jobRepository;

	@Builder
	public RemoveJobApplicationCommandExecutor(JobAggregateRepository jobRepository) {
		this.jobRepository = jobRepository;
	}

	@Override
	public Job execute(BaseCommand baseCommand) {
		if (baseCommand instanceof RemoveJobApplicationCommand) {
			RemoveJobApplicationCommand command = (RemoveJobApplicationCommand) baseCommand;
			Job jobAggregate = this.jobRepository.findByIdWithJobApplications(new JobId(command.getJobId()));

			Optional<JobApplication> optional = jobAggregate
				.getJobApplications()
				.stream()
				.filter(jobApplication -> jobApplication.getId().equal(new JobApplicationId(command.getJobApplicationId())))
				.findAny();

			if (optional.isPresent()) {
				JobApplication jobApplication = optional.get();

				jobAggregate.removeJobApplication(jobApplication);

				return this.jobRepository.save(jobAggregate);
			} else {
				throw new JobApplicationNotFound("application doesn't exit in job");
			}
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
				.filter(jobApplication -> jobApplication.getId().equal(new JobApplicationId(command.getJobApplicationId())))
				.findAny();

			if (optional.isPresent()) {
				JobApplication jobApplication = optional.get();
				jobAggregate.acceptJobApplication(jobApplication);

				Job updatedJob = this.jobRepository.save(jobAggregate);
			}
		}
	}
}
