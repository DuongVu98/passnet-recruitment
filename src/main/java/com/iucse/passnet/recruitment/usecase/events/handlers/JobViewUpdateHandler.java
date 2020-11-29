package com.iucse.passnet.recruitment.usecase.events.handlers;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.viewrepos.JobViewRepository;
import com.iucse.passnet.recruitment.domain.views.JobView;
import com.iucse.passnet.recruitment.domain.views.LiteJobApplicationView;
import com.iucse.passnet.recruitment.usecase.events.events.DomainEvent;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j(topic = "[JobViewUpdateHandler]")
public class JobViewUpdateHandler implements IEventHandler {
	private final JobViewRepository jobViewRepository;

	@Autowired
	public JobViewUpdateHandler(JobViewRepository jobViewRepository) {
		this.jobViewRepository = jobViewRepository;
	}

	@Override
	public void handle(DomainEvent event) {
		switch (event.getEventTypes()) {
			case TeacherPostedJob:
				this.updateFromJob(event.getAggregate());
				break;
			case StudentAppliedJob:
				break;
			case TeacherAcceptedJob:
				break;
			default:
				break;
		}
	}

	private void updateFromJob(Job aggregate) {
		List<LiteJobApplicationView> liteJobApplicationViews = aggregate
			.getJobApplications()
			.stream()
			.map(
				application ->
					LiteJobApplicationView
						.builder()
						.studentId(application.getApplicationOwner().getValue())
						.applicationState(application.getApplicationState().getValue().name())
						.build()
			)
			.collect(Collectors.toList());

		JobView newJobView = JobView
			.builder()
			.id(aggregate.getId().getValue())
			.teacherId(aggregate.getJobOwner().getValue())
			.courseName(aggregate.getCourseName().getValue())
			.jobTitle(aggregate.getJobName().getValue())
			.requirement(aggregate.getJobRequirement().getValue())
			.content(aggregate.getContent().getValue())
			.semester(aggregate.getSemester().getValue())
			.jobApplicationsView(liteJobApplicationViews)
			.build();

		this.jobViewRepository.save(newJobView);
	}
}
