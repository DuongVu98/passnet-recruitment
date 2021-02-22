package com.iucse.passnet.recruitment.usecase.queries;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.aggregate.job.entities.JobApplication;
import com.iucse.passnet.recruitment.domain.aggregate.job.vos.JobApplicationId;
import com.iucse.passnet.recruitment.domain.aggregate.job.vos.JobId;
import com.iucse.passnet.recruitment.domain.annotation.Cached;
import com.iucse.passnet.recruitment.domain.repositories.JobAggregateRepository;
import com.iucse.passnet.recruitment.domain.repositories.JobApplicationRepository;
import com.iucse.passnet.recruitment.domain.viewrepos.JobApplicationListViewRepository;
import com.iucse.passnet.recruitment.domain.views.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ViewQuery {
	private final JobAggregateRepository jobEntityRepository;
	private final JobApplicationRepository jobApplicationEntityRepository;

	@Autowired
	@Qualifier("posted-jobs-view-id")
	private String postedJobsViewId;

	@Autowired
	public ViewQuery(
		JobAggregateRepository jobEntityRepository,
		JobApplicationRepository jobApplicationEntityRepository
	) {
		this.jobEntityRepository = jobEntityRepository;
		this.jobApplicationEntityRepository = jobApplicationEntityRepository;
	}

	@Cached(ViewTypes.JOB_VIEW)
	public JobView queryJobView(String id) throws NullPointerException {
		Job aggregate = this.jobEntityRepository.findByIdWithJobApplications(new JobId(id));
		return new JobView(aggregate, id);
	}

	@Cached(ViewTypes.JOB_APPLICATION_VIEW)
	public JobApplicationView queryJobApplicationView(String id) {
		Optional<JobApplication> optional = this.jobApplicationEntityRepository.findById(new JobApplicationId(id));
		if (optional.isPresent()) {
			JobApplication jobApplication = optional.get();
			return JobApplicationView
				.builder()
				.id(id)
				.studentId(jobApplication.getApplicationOwner().getValue())
				.content(jobApplication.getContent().getValue())
				.letter(jobApplication.getLetter().getValue())
				.state(jobApplication.getApplicationState().getValue().name())
				.build();
		} else {
			return null;
		}
	}

	@Cached(ViewTypes.POSTED_JOBS_VIEW)
	public PostedJobsView queryPostedJobsView() {
		List<LiteJobView> liteJobViewList =
			this.jobEntityRepository.findAll()
				.stream()
				.map(
					job ->
						LiteJobView
							.builder()
							.id(job.getId().getValue())
							.jobTitle(job.getJobName().getValue())
							.semester(job.getSemester().getValue())
							.department("")
							.courseName(job.getCourseName().getValue())
							.appliedAmount(job.getJobApplications().size())
							.build()
				)
				.collect(Collectors.toList());

		return PostedJobsView.builder().id(this.postedJobsViewId).litePostedJobs(liteJobViewList).build();
	}

	@Cached(ViewTypes.USER_OWN_JOB_VIEW)
	public OwnedJobListView queryUserOwnJob(String uid) {
		List<LiteJobView> liteJobViewList =
			this.jobEntityRepository.findAll()
				.stream()
				.filter(job -> job.getJobOwner().getValue().equals(uid))
				.map(
					job ->
						LiteJobView
							.builder()
							.id(job.getId().getValue())
							.jobTitle(job.getJobName().getValue())
							.semester(job.getSemester().getValue())
							.department("")
							.courseName(job.getCourseName().getValue())
							.appliedAmount(job.getJobApplications().size())
							.build()
				)
				.collect(Collectors.toList());
		return OwnedJobListView.builder().id(uid).teacherId(uid).litePostedJobs(liteJobViewList).build();
	}

	@Cached(ViewTypes.JOB_APPLICATION_LIST_VIEW)
	public JobApplicationListView queryJobApplicationListView(String jobId) {
		Job aggregate = this.jobEntityRepository.findByIdWithJobApplications(new JobId(jobId));
		return new JobApplicationListView(aggregate);
	}
}
