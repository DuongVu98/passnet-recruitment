package com.iucse.passnet.recruitment.domain.views;

import lombok.Builder;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@Builder
@RedisHash("posted_jobs_view")
public class PostedJobsView extends CacheableView{
    private List<LiteJobView> litePostedJobs;
}