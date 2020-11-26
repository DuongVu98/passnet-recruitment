package com.iucse.passnet.recruitment.domain.views;

import lombok.Builder;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@RedisHash("posted_jobs_view")
public class PostedJobsView extends CacheableView{
    private List<LiteJobView> litePostedJobs;

    @Builder
    public PostedJobsView(String id, List<LiteJobView> litePostedJobs) {
        super(id);
        this.litePostedJobs = litePostedJobs;
    }
}

@Builder
@AllArgsConstructor
class LiteJobView {
    String courseName;
    String jobTitle;
    String semester;
    String department;
}