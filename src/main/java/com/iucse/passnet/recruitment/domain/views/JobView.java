package com.iucse.passnet.recruitment.domain.views;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

@Getter
@NoArgsConstructor
@RedisHash(value = "job_view", timeToLive = 20)
public class JobView extends CacheableView {
	private String jobTitle;
	private String teacherId;
	private String courseName;
	private String content;
	private String requirement;
	private String semester;

	@Setter
	private List<LiteJobApplicationView> jobApplicationsView;

	@Builder
	public JobView(
		String id,
		String jobTitle,
		String teacherId,
		String courseName,
		String content,
		String requirement,
		String semester,
		List<LiteJobApplicationView> jobApplicationsView
	) {
		super(id);
		this.jobTitle = jobTitle;
		this.teacherId = teacherId;
		this.courseName = courseName;
		this.content = content;
		this.requirement = requirement;
		this.semester = semester;
		this.jobApplicationsView = jobApplicationsView;
	}

	public JobView(Job aggregate, String viewId) {
		this.id = viewId;
		this.jobTitle = aggregate.getJobName().getValue();
		this.teacherId = aggregate.getJobOwner().getValue();
		this.content = aggregate.getContent().getValue();
		this.courseName = aggregate.getCourseName().getValue();
		this.requirement = aggregate.getJobRequirement().getValue();
		this.semester = aggregate.getSemester().getValue();
		this.jobApplicationsView = new ArrayList<>();
		aggregate
			.getJobApplications()
			.forEach(
				jobApplication -> {
					this.jobApplicationsView.add(
							LiteJobApplicationView
								.builder()
								.studentId(jobApplication.getApplicationOwner().getValue())
								.applicationState(jobApplication.getApplicationState().getValue().name())
								.build()
						);
				}
			);
	}
}
