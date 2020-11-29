package com.iucse.passnet.recruitment.usecase.events.handlers;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.aggregate.job.entities.JobApplication;
import com.iucse.passnet.recruitment.domain.aggregate.job.vos.JobApplicationId;
import com.iucse.passnet.recruitment.domain.viewrepos.JobApplicationViewRepository;
import com.iucse.passnet.recruitment.domain.views.JobApplicationView;
import com.iucse.passnet.recruitment.usecase.events.events.DomainEvent;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j(topic = "[JobApplicationViewUpdateHandler]")
public class JobApplicationViewUpdateHandler implements IEventHandler {
	private final JobApplicationViewRepository jobApplicationViewRepository;

	private JobApplicationId jobApplicationId;
	private Job aggregate;

	@Autowired
	public JobApplicationViewUpdateHandler(JobApplicationViewRepository jobApplicationViewRepository) {
		this.jobApplicationViewRepository = jobApplicationViewRepository;
	}

	@Override
	public void handle(DomainEvent event) {
		switch (event.getEventTypes()) {
			case TeacherPostedJob:
				break;
			case StudentAppliedJob:
				this.aggregate = event.getAggregate();
				this.jobApplicationId = (JobApplicationId) event.getEntityId();
				this.updateFromAggregate();
				break;
			case TeacherAcceptedJob:
				break;
			default:
				break;
		}
	}

	private void updateFromAggregate() {
		log.info("update job application view");
		Optional<JobApplication> optional =
			this.aggregate.getJobApplications()
				.stream()
				.filter(jobApplication -> jobApplication.getId().equal(this.jobApplicationId))
				.findFirst();
		if (optional.isPresent()) {
			JobApplication jobApplication = optional.get();
			JobApplicationView jobApplicationView = JobApplicationView
				.builder()
				.id(this.jobApplicationId.getValue())
				.studentId(jobApplication.getApplicationOwner().getValue())
				.letter(jobApplication.getLetter().getValue())
				.content(jobApplication.getContent().getValue())
				.state(jobApplication.getApplicationState().getValue().name())
				.build();
			this.jobApplicationViewRepository.save(jobApplicationView);
		}
	}
}
