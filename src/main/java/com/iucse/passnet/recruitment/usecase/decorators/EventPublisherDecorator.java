package com.iucse.passnet.recruitment.usecase.decorators;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.aggregate.job.entities.JobApplication;
import com.iucse.passnet.recruitment.domain.aggregate.job.vos.JobApplicationId;
import com.iucse.passnet.recruitment.domain.commands.AcceptJobApplicationCommand;
import com.iucse.passnet.recruitment.domain.commands.BaseCommand;
import com.iucse.passnet.recruitment.domain.commands.RemoveJobApplicationCommand;
import com.iucse.passnet.recruitment.domain.events.produce.AcceptStudentApplicationEvent;
import com.iucse.passnet.recruitment.domain.events.produce.RemoveStudentApplicationEvent;
import com.iucse.passnet.recruitment.domain.repositories.JobApplicationRepository;
import com.iucse.passnet.recruitment.usecase.executors.CommandExecutor;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.greenrobot.eventbus.EventBus;

@Slf4j(topic = "EventPublisherDecorator")
public class EventPublisherDecorator extends CommandExecutorDecorator {
	private final HttpServletRequest request;
	private final JobApplicationRepository jobApplicationRepository;

	@Builder
	public EventPublisherDecorator(CommandExecutor commandExecutor, HttpServletRequest request, JobApplicationRepository jobApplicationRepository) {
		super(commandExecutor);
		this.request = request;
		this.jobApplicationRepository = jobApplicationRepository;
	}

	@Override
	public Job execute(BaseCommand command) {
		Job job = this.commandExecutor.execute(command);
		String eventId = (String) this.request.getSession().getAttribute("eventId");
		this.publishEvent(job, command, eventId);
		return job;
	}

	private void publishEvent(Job updatedJob, BaseCommand baseCommand, String eventId) {
		if (baseCommand instanceof AcceptJobApplicationCommand) {
			AcceptJobApplicationCommand command = (AcceptJobApplicationCommand) baseCommand;
			Optional<JobApplication> optionalAddedApplication = jobApplicationRepository.findById(
				new JobApplicationId(command.getJobApplicationId())
			);

			this.publish(
					AcceptStudentApplicationEvent
						.builder()
						.eventId(eventId)
						.jobId(updatedJob.getId().getValue())
						.taId(optionalAddedApplication.get().getApplicationOwner().getValue())
						.build()
				);
		} else if (baseCommand instanceof RemoveJobApplicationCommand) {
			RemoveJobApplicationCommand command = (RemoveJobApplicationCommand) baseCommand;
			Optional<JobApplication> optionalRemovedApplication = jobApplicationRepository.findById(
				new JobApplicationId(command.getJobApplicationId())
			);

			this.publish(
					RemoveStudentApplicationEvent
						.builder()
						.eventId(eventId)
						.jobId(updatedJob.getId().getValue())
						.taId(optionalRemovedApplication.get().getApplicationOwner().getValue())
						.build()
				);
		} else {
			log.warn("base command is in wrong type for publishing event");
		}
	}

	private void publish(Object event) {
		EventBus.getDefault().post(event);
	}
}
