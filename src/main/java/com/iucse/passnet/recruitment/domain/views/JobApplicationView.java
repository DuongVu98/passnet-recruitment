package com.iucse.passnet.recruitment.domain.views;

import lombok.Builder;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@Builder
@AllArgsConstructor
@RedisHash("job_application_view")
public class JobApplicationView extends CacheableView {
    private String studentId;
    private String letter;
    private String content;
    private String state;
}
