package com.iucse.passnet.recruitment.usecase.events.handlers;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.viewrepos.PostedJobsViewRepository;
import com.iucse.passnet.recruitment.domain.views.LiteJobView;
import com.iucse.passnet.recruitment.domain.views.PostedJobsView;
import com.iucse.passnet.recruitment.usecase.events.events.DomainEvent;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PostedJobsViewUpdateHandler implements IEventHandler {
	private final PostedJobsViewRepository postedJobsViewRepository;

	@Autowired
	@Qualifier("posted-jobs-view-id")
	private String postedJobsViewId;

	@Autowired
	public PostedJobsViewUpdateHandler(PostedJobsViewRepository postedJobsViewRepository) {
		this.postedJobsViewRepository = postedJobsViewRepository;
	}

	@Override
	public void handle(DomainEvent event) {
		switch (event.getEventTypes()) {
			case TeacherPostedJob:
				this.updateAddJob(event.getAggregate());
				break;
			case StudentAppliedJob:
				this.updateFromJob(event.getAggregate());
				break;
			default:
				break;
		}
	}

	private void updateAddJob(Job aggregate) {
		PostedJobsView postedJobsView = this.createPostedJobsViewIfNotExist();
		LiteJobView newLiteJobView = LiteJobView
			.builder()
			.id(aggregate.getId().getValue())
			.jobTitle(aggregate.getJobName().getValue())
			.courseName(aggregate.getCourseName().getValue())
			.department("")
			.semester(aggregate.getSemester().getValue())
			.appliedAmount(0)
			.build();

		postedJobsView.getLitePostedJobs().add(newLiteJobView);

		this.postedJobsViewRepository.save(postedJobsView);
	}

	private void updateFromJob(Job aggregate) {
		PostedJobsView postedJobsView = this.createPostedJobsViewIfNotExist();

		Optional<LiteJobView> jobViewOptional = postedJobsView
			.getLitePostedJobs()
			.stream()
			.filter(liteJobView -> liteJobView.getId().equals(aggregate.getId().getValue()))
			.findFirst();

		if (jobViewOptional.isPresent()) {
			LiteJobView liteJobView = jobViewOptional.get();

			liteJobView.setAppliedAmount(liteJobView.getAppliedAmount() + 1);

			this.postedJobsViewRepository.save(postedJobsView);
		}
	}

	private PostedJobsView createPostedJobsViewIfNotExist() {
		Optional<PostedJobsView> optional = this.postedJobsViewRepository.findById(this.postedJobsViewId);

		return optional.orElseGet(
			() ->
				this.postedJobsViewRepository.save(
						PostedJobsView.builder().id(this.postedJobsViewId).litePostedJobs(new ArrayList<>()).build()
					)
		);
	}
}
