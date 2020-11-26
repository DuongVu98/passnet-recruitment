package com.iucse.passnet.recruitment.domain.views;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import java.util.ArrayList;
import java.util.List;

@Getter
@RedisHash(value = "job_view", timeToLive = 10)
public class JobView extends CacheableView {
    private String jobTitle;
    private String teacherId;
    private String courseName;
    private String content;
    private String requirement;
    private String semester;
    private List<LiteJobApplicationView> jobApplicationsView;

    @Builder
    public JobView(String id, String jobTitle, String teacherId, String courseName, String content, String requirement, String semester, List<LiteJobApplicationView> jobApplicationsView) {
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
        aggregate.getJobApplications().forEach(jobApplication -> {
            this.jobApplicationsView.add(
                    LiteJobApplicationView.builder()
                            .studentId(jobApplication.getId().getValue())
                            .applicationState(jobApplication.getApplicationState().getValue().name())
                            .build()
            );
        });
    }
}