package com.iucse.passnet.recruitment.usecase.executors;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.aggregate.job.entities.JobApplication;
import com.iucse.passnet.recruitment.domain.aggregate.job.vos.JobApplicationId;
import com.iucse.passnet.recruitment.domain.aggregate.job.vos.JobId;
import com.iucse.passnet.recruitment.domain.commands.TeacherRemoveStudentJobApplicationCommand;
import com.iucse.passnet.recruitment.domain.events.produce.AcceptStudentApplicationEvent;
import com.iucse.passnet.recruitment.domain.events.produce.RemoveStudentApplicationEvent;
import com.iucse.passnet.recruitment.domain.exceptions.JobApplicationNotFound;
import com.iucse.passnet.recruitment.domain.repositories.JobAggregateRepository;
import com.iucse.passnet.recruitment.usecase.grpc.RecruitmentSagaGateway;
import java.util.Optional;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "[TeacherRemoveStudentJobApplicationCommandExecutor]")
public class TeacherRemoveStudentJobApplicationCommandExecutor
	extends AbstractCommandExecutor<TeacherRemoveStudentJobApplicationCommand, Job> {
	private final RecruitmentSagaGateway recruitmentSagaGateway;

	@Builder
	public TeacherRemoveStudentJobApplicationCommandExecutor(
		JobAggregateRepository aggregateRepository,
		RecruitmentSagaGateway recruitmentSagaGateway
	) {
		super(aggregateRepository);
		this.recruitmentSagaGateway = recruitmentSagaGateway;
	}

	@Override
	public Job execute(TeacherRemoveStudentJobApplicationCommand command) throws Throwable {
		Job jobAggregate = this.aggregateRepository.findByIdWithJobApplications(new JobId(command.getJobId()));
		Optional<JobApplication> optional = jobAggregate
			.getJobApplications()
			.stream()
			.filter(jobApplication -> jobApplication.getId().equal(new JobApplicationId(command.getJobApplicationId())))
			.findAny();

		if (optional.isPresent()) {
			JobApplication jobApplication = optional.get();

			jobAggregate.removeJobApplication(jobApplication);

			Job updatedJob = this.aggregateRepository.save(jobAggregate);

			recruitmentSagaGateway.produceRemoveStudentApplicationEvent(
				RemoveStudentApplicationEvent
					.builder()
					.jobId(updatedJob.getId().getValue())
					.taId(jobApplication.getApplicationOwner().getValue())
					.build()
			);

			return updatedJob;
		} else {
			throw new JobApplicationNotFound("application doesn't exit in job");
		}
	}
}
