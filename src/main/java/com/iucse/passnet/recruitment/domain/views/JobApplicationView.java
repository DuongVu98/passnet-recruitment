package com.iucse.passnet.recruitment.domain.views;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.aggregate.job.entities.JobApplication;
import com.iucse.passnet.recruitment.domain.aggregate.job.vos.JobApplicationId;
import com.iucse.passnet.recruitment.domain.aggregate.job.vos.JobId;
import lombok.Builder;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.util.Optional;

@RedisHash(value = "job_application_view", timeToLive = 20)
public class JobApplicationView extends CacheableView {
    private String studentId;
    private String letter;
    private String content;
    private String state;

    @Builder
    public JobApplicationView(String id, String studentId, String letter, String content, String state) {
        super(id);
        this.studentId = studentId;
        this.letter = letter;
        this.content = content;
        this.state = state;
    }

    public JobApplicationView(Job aggregate, String id) {
        Optional<JobApplication> optional = aggregate.getJobApplications().stream().filter(jobApplication -> jobApplication.getId().equal(new JobApplicationId(id))).findAny();
        if (optional.isPresent()) {
            JobApplication jobApplication = optional.get();

            this.studentId = jobApplication.getApplicationOwner().getValue();
            this.content = jobApplication.getContent().getValue();
            this.letter = jobApplication.getLetter().getValue();
            this.state = jobApplication.getApplicationState().getValue().name();
            this.id = id;
        }
    }
}
