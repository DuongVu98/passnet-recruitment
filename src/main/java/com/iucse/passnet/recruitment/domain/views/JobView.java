package com.iucse.passnet.recruitment.domain.views;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@Getter
@RedisHash(value = "job_view")
public class JobView extends CacheableView{
    private String jobTitle;
    private String teacherId;
    private String courseName;
    private String content;
    private String requirement;
    private String semester;
    private List<LiteJobApplicationView> jobApplicationsView;

    @Builder
    public JobView(String id, Long timeToLive, String jobTitle, String teacherId, String courseName, String content, String requirement, String semester, List<LiteJobApplicationView> jobApplicationsView) {
        super(id, timeToLive);
        this.jobTitle = jobTitle;
        this.teacherId = teacherId;
        this.courseName = courseName;
        this.content = content;
        this.requirement = requirement;
        this.semester = semester;
        this.jobApplicationsView = jobApplicationsView;
    }
}