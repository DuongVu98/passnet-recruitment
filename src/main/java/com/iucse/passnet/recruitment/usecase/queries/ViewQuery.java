package com.iucse.passnet.recruitment.usecase.queries;

import com.iucse.passnet.recruitment.domain.aggregate.entities.Job;
import com.iucse.passnet.recruitment.domain.aggregate.entities.JobApplication;
import com.iucse.passnet.recruitment.domain.aggregate.vos.JobApplicationId;
import com.iucse.passnet.recruitment.domain.aggregate.vos.JobId;
import com.iucse.passnet.recruitment.domain.helpers.ViewTypes;
import com.iucse.passnet.recruitment.domain.repositories.JobAggregateRepository;
import com.iucse.passnet.recruitment.domain.repositories.JobApplicationRepository;
import com.iucse.passnet.recruitment.domain.views.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class ViewQuery {
	private final JobAggregateRepository jobEntityRepository;
	private final JobApplicationRepository jobApplicationEntityRepository;

	@Value("${view.posted-jobs.id}")
	private String postedJobsViewId;

	@Autowired
	public ViewQuery(JobAggregateRepository jobEntityRepository, JobApplicationRepository jobApplicationEntityRepository) {
		this.jobEntityRepository = jobEntityRepository;
		this.jobApplicationEntityRepository = jobApplicationEntityRepository;
	}

	@Cacheable(value = ViewTypes.JOB_VIEW, key = "#id", sync = true)
	public JobView queryJobView(String id) throws NullPointerException {
		Job aggregate = this.jobEntityRepository.findByIdWithJobApplications(new JobId(id));
		return new JobView(aggregate, id);
	}

	@Cacheable(value = ViewTypes.JOB_APPLICATION_VIEW, key = "#id", sync = true)
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

	@Cacheable(value = ViewTypes.POSTED_JOBS_VIEW, sync = true)
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

	@Cacheable(value = ViewTypes.USER_OWN_JOB_VIEW, key = "#uid")
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

	@Cacheable(value = ViewTypes.JOB_APPLICATION_LIST_VIEW, key = "#jobId")
	public JobApplicationListView queryJobApplicationListView(String jobId) {
		Job aggregate = this.jobEntityRepository.findByIdWithJobApplications(new JobId(jobId));
		return new JobApplicationListView(aggregate);
	}
}
