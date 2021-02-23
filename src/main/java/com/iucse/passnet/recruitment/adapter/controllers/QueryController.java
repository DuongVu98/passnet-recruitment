package com.iucse.passnet.recruitment.adapter.controllers;

import com.iucse.passnet.recruitment.domain.exceptions.JobNotFoundException;
import com.iucse.passnet.recruitment.domain.exceptions.NullIdentifierException;
import com.iucse.passnet.recruitment.domain.viewrepos.*;
import com.iucse.passnet.recruitment.domain.views.*;
import com.iucse.passnet.recruitment.usecase.queries.ViewQuery;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class QueryController {
	private final JobViewRepository jobViewRepository;
	private final JobApplicationViewRepository jobApplicationViewRepository;
	private final PostedJobsViewRepository postedJobsViewRepository;
	private final OwnJobListViewRepository ownJobListViewRepository;
	private final JobApplicationListViewRepository jobApplicationListViewRepository;
	private final ViewQuery viewQuery;

	@Autowired
	@Qualifier("posted-jobs-view-id")
	private String postedJobsViewId;

	@Autowired
	public QueryController(
		JobViewRepository jobViewRepository,
		JobApplicationViewRepository jobApplicationViewRepository,
		PostedJobsViewRepository postedJobsViewRepository,
		OwnJobListViewRepository ownJobListViewRepository,
		JobApplicationListViewRepository jobApplicationListViewRepository,
		ViewQuery viewQuery
	) {
		this.jobViewRepository = jobViewRepository;
		this.jobApplicationViewRepository = jobApplicationViewRepository;
		this.postedJobsViewRepository = postedJobsViewRepository;
		this.ownJobListViewRepository = ownJobListViewRepository;
		this.jobApplicationListViewRepository = jobApplicationListViewRepository;
		this.viewQuery = viewQuery;
	}

	public JobView getJobView(String id) throws NullIdentifierException, JobNotFoundException {
//		if (id != null) {
//			if (this.jobViewRepository.findById(id).isPresent()) {
//				return this.jobViewRepository.findById(id).get();
//			} else {
//				try {
//					return this.viewQuery.queryJobView(id);
//				} catch (NullPointerException exception) {
//					throw new JobNotFoundException(String.format("Job with id %s not found", id));
//				}
//			}
//		} else {
//			throw new NullIdentifierException("Job id is null");
//		}
		return this.viewQuery.queryJobView(id);
	}

	public JobApplicationView getJobApplicationView(String id) {
//		Optional<JobApplicationView> optional = this.jobApplicationViewRepository.findById(id);
//		return optional.orElseGet(() -> this.viewQuery.queryJobApplicationView(id));
		return this.viewQuery.queryJobApplicationView(id);
	}

	public PostedJobsView getPostedJobsView() {
//		Optional<PostedJobsView> optional = this.postedJobsViewRepository.findById(this.postedJobsViewId);
//		return optional.orElseGet(this.viewQuery::queryPostedJobsView);
		return this.viewQuery.queryPostedJobsView();
	}

	public OwnedJobListView getPostedJobsByUserView(String uid) {
//		Optional<OwnedJobListView> viewOptional = Optional.ofNullable(
//			this.ownJobListViewRepository.findByTeacherId(uid)
//		);
//		return viewOptional.orElseGet(() -> viewQuery.queryUserOwnJob(uid));
		return this.viewQuery.queryUserOwnJob(uid);
	}

	public JobApplicationListView getJobApplicationListView(String jobId) {
//		Optional<JobApplicationListView> jobApplicationListViewOptional =
//			this.jobApplicationListViewRepository.findById(jobId);
//		return jobApplicationListViewOptional.orElseGet(() -> this.viewQuery.queryJobApplicationListView(jobId));

		return this.viewQuery.queryJobApplicationListView(jobId);
	}
}
