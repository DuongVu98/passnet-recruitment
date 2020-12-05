package com.iucse.passnet.recruitment.usecase.commands.handlers;

import com.iucse.passnet.recruitment.adapter.channel.DomainEventBus;
import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.aggregate.job.entities.JobApplication;
import com.iucse.passnet.recruitment.domain.aggregate.job.vos.JobApplicationId;
import com.iucse.passnet.recruitment.domain.aggregate.job.vos.JobId;
import com.iucse.passnet.recruitment.domain.repositories.JobAggregateRepository;
import com.iucse.passnet.recruitment.usecase.commands.requests.TeacherAcceptStudentJobApplicationCommand;
import com.iucse.passnet.recruitment.usecase.events.events.DomainEvent;
import com.iucse.passnet.recruitment.usecase.events.events.EventTypes;
import java.util.Optional;
import lombok.Builder;

public class TeacherAcceptStudentApplicationCommandHandler extends AbstractJobAggregateCommandHandler<Job> {
	private final TeacherAcceptStudentJobApplicationCommand command;

	@Builder
	public TeacherAcceptStudentApplicationCommandHandler(
		JobAggregateRepository aggregateRepository,
		EventTypes eventToApply,
		DomainEventBus eventBus,
		TeacherAcceptStudentJobApplicationCommand command
	) {
		super(aggregateRepository, eventToApply, eventBus);
		this.command = command;
	}

	@Override
	public DomainEvent execute() {
		Job jobAggregate = this.aggregateRepository.findByIdWithJobApplications(new JobId(this.command.getJobId()));
		Optional<JobApplication> optional = jobAggregate
			.getJobApplications()
			.stream()
			.filter(
				jobApplication -> jobApplication.getId().equal(new JobApplicationId(this.command.getJobApplicationId()))
			)
			.findAny();

		if (optional.isPresent()) {
			JobApplication jobApplication = optional.get();
			jobAggregate.acceptJobApplication(jobApplication);
			return new DomainEvent(this.getEventToApply(), jobAggregate, jobApplication.getId());
		} else {
			return null;
		}
	}
}
