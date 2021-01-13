package com.iucse.passnet.recruitment.domain.views;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
@RedisHash(value = "job_application_list_view", timeToLive = 1)
public class JobApplicationListView extends CacheableView {
    private JobView jobView;
    private List<JobApplicationView> jobApplicationViewList;

    @Builder
    public JobApplicationListView(String id, JobView jobView, List<JobApplicationView> jobApplicationViewList) {
        super(id);
        this.jobView = jobView;
        this.jobApplicationViewList = jobApplicationViewList;
    }

    public JobApplicationListView(Job aggregate) {
        this.id = aggregate.getId().getValue();
        this.jobView = new JobView(aggregate, aggregate.getId().getValue());
        this.jobApplicationViewList = aggregate.getJobApplications().stream().map(jobApplication -> new JobApplicationView(aggregate, jobApplication.getId().getValue())).collect(Collectors.toList());
    }
}
