package com.iucse.passnet.recruitment.adapter.controllers;

import com.iucse.passnet.recruitment.domain.viewrepos.JobApplicationViewRepository;
import com.iucse.passnet.recruitment.domain.viewrepos.JobViewRepository;
import com.iucse.passnet.recruitment.domain.viewrepos.PostedJobsViewRepository;
import com.iucse.passnet.recruitment.domain.views.JobApplicationView;
import com.iucse.passnet.recruitment.domain.views.JobView;
import com.iucse.passnet.recruitment.domain.views.PostedJobsView;
import com.iucse.passnet.recruitment.usecase.queries.ViewQuery;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class QueryController {
	private final JobViewRepository jobViewRepository;
	private final JobApplicationViewRepository jobApplicationViewRepository;
	private final PostedJobsViewRepository postedJobsViewRepository;
	private final ViewQuery viewQuery;

	@Value("view.posted-jobs.id")
	private String postedJobsViewId;

	@Autowired
	public QueryController(
		JobViewRepository jobViewRepository,
		JobApplicationViewRepository jobApplicationViewRepository,
		PostedJobsViewRepository postedJobsViewRepository,
		ViewQuery viewQuery
	) {
		this.jobViewRepository = jobViewRepository;
		this.jobApplicationViewRepository = jobApplicationViewRepository;
		this.postedJobsViewRepository = postedJobsViewRepository;
		this.viewQuery = viewQuery;
	}

	public JobView getJobView(String id) {
		if (this.jobViewRepository.findById(id).isPresent()) {
			return this.jobViewRepository.findById(id).get();
		} else {
			return this.viewQuery.queryJobView(id);
		}
	}

	public JobApplicationView getJobApplicationView(String id) {
		Optional<JobApplicationView> optional = this.jobApplicationViewRepository.findById(id);
		return optional.orElseGet(() -> this.viewQuery.queryJobApplicationView(id));
	}

	public PostedJobsView getPostedJobsView() {
		Optional<PostedJobsView> optional = this.postedJobsViewRepository.findById(this.postedJobsViewId);
		return optional.orElseGet(this.viewQuery::queryPostedJobsView);
	}
}
