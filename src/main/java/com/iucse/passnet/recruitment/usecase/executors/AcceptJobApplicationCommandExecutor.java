package com.iucse.passnet.recruitment.usecase.executors;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.aggregate.job.entities.JobApplication;
import com.iucse.passnet.recruitment.domain.aggregate.job.vos.JobApplicationId;
import com.iucse.passnet.recruitment.domain.aggregate.job.vos.JobId;
import com.iucse.passnet.recruitment.domain.commands.AcceptJobApplicationCommand;
import com.iucse.passnet.recruitment.domain.commands.BaseCommand;
import com.iucse.passnet.recruitment.domain.compensating.AcceptJobApplicationCompensating;
import com.iucse.passnet.recruitment.domain.compensating.CompensatingCommand;
import com.iucse.passnet.recruitment.domain.events.produce.AcceptStudentApplicationEvent;
import com.iucse.passnet.recruitment.domain.exceptions.JobApplicationNotFound;
import com.iucse.passnet.recruitment.domain.exceptions.WrongCommandTypeException;
import com.iucse.passnet.recruitment.domain.repositories.JobAggregateRepository;
import java.util.Optional;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.greenrobot.eventbus.EventBus;

@Slf4j(topic = "[AcceptJobApplicationCommandExecutor]")
public class AcceptJobApplicationCommandExecutor implements CommandExecutor, CompensatingHandler {
	private final JobAggregateRepository jobRepository;

	@Builder
	public AcceptJobApplicationCommandExecutor(JobAggregateRepository jobRepository) {
		this.jobRepository = jobRepository;
	}

	@Override
	public Job execute(BaseCommand baseCommand) {
		if (baseCommand instanceof AcceptJobApplicationCommand) {
			AcceptJobApplicationCommand command = (AcceptJobApplicationCommand) baseCommand;
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

				//				EventBus
				//					.getDefault()
				//					.post(
				//						AcceptStudentApplicationEvent
				//							.builder()
				//							.jobId(updatedJob.getId().getValue())
				//							.taId(jobApplication.getApplicationOwner().getValue())
				//							.build()
				//					);

				return updatedJob;
			} else {
				throw new JobApplicationNotFound("application {} doesn't exit in job");
			}
		} else {
			throw new WrongCommandTypeException("command must be TeacherAcceptStudentJobApplicationCommand");
		}
	}

	@Override
	public void reverse(CompensatingCommand compensatingCommand) {
		if (compensatingCommand instanceof AcceptJobApplicationCompensating) {
			AcceptJobApplicationCompensating command = (AcceptJobApplicationCompensating) compensatingCommand;

			Job jobAggregate = this.jobRepository.findByIdWithJobApplications(new JobId(command.getJobId()));

			Optional<JobApplication> optional = jobAggregate
				.getJobApplications()
				.stream()
				.filter(jobApplication -> jobApplication.getId().equal(new JobApplicationId(command.getJobApplicationId())))
				.findAny();

			if (optional.isPresent()) {
				JobApplication jobApplication = optional.get();
				jobAggregate.removeJobApplication(jobApplication);

				Job updatedJob = this.jobRepository.save(jobAggregate);
			}
		}
	}
}
