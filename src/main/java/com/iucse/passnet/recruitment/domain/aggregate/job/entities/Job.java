package com.iucse.passnet.recruitment.domain.aggregate.job.entities;

import com.iucse.passnet.recruitment.domain.aggregate.job.vos.*;
import com.iucse.passnet.recruitment.domain.exceptions.JobApplicationNotFound;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "jobs")
@Slf4j(topic = "[Job]")
public class Job {
	@EmbeddedId
	@AttributeOverride(name = "value", column = @Column(name = "id"))
	private JobId id;

	@Embedded
	@AttributeOverride(name = "value", column = @Column(name = "job_owner"))
	private UserId jobOwner;

	@Embedded
	@AttributeOverride(name = "value", column = @Column(name = "job_name"))
	private JobName jobName;

	@Embedded
	@AttributeOverride(name = "value", column = @Column(name = "course_name"))
	private CourseName courseName;

	@Embedded
	@AttributeOverride(name = "value", column = @Column(name = "semester"))
	private Semester semester;

	@Embedded
	@AttributeOverride(name = "value", column = @Column(name = "job_requirement"))
	private JobRequirement jobRequirement;

	@Embedded
	@AttributeOverride(name = "value", column = @Column(name = "job_content"))
	private Content content;

	@Builder.Default
	@OneToMany(mappedBy = "job", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE }, fetch = FetchType.LAZY)
	private List<JobApplication> jobApplications = new ArrayList<>();

	public void receiveJobApplication(JobApplication application) {
		application.applyJob(this);
		this.jobApplications.add(application);
	}

	public void acceptJobApplication(JobApplication application) throws JobApplicationNotFound {
		if (this.jobApplications.contains(application)) {
			log.info("application list contains this application");

			this.jobApplications =
				this.jobApplications.stream()
					.map(
						currentApplication -> {
							if (currentApplication.getId().equal(application.getId())) {
								log.info("change state!");

								currentApplication.accepted();
								log.info("currentApplication after accepted: {}", currentApplication.getApplicationState().getValue());
							}
							return currentApplication;
						}
					)
					.collect(Collectors.toList());
		} else {
			throw new JobApplicationNotFound(String.format("job application with id: %s not found in this job", application.getId()));
		}
	}

	public void removeJobApplication(JobApplication application) throws JobApplicationNotFound {
		if (this.jobApplications.contains(application)) {
			log.info("application list contains this application");

			this.jobApplications =
				this.jobApplications.stream()
					.map(
						currentApplication -> {
							if (currentApplication.getId().equal(application.getId())) {
								log.info("change state!");

								currentApplication.removed();
								log.info(
									"currentApplication after accepted: {}",
									currentApplication.getApplicationState().getValue()
								);
							}
							return currentApplication;
						}
					)
					.collect(Collectors.toList());
		} else {
			throw new JobApplicationNotFound(
				String.format("job application with id: %s not found in this job", application.getId())
			);
		}
	}
}
