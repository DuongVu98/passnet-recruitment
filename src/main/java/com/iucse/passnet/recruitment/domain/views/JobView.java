package com.iucse.passnet.recruitment.domain.views;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@Builder
@AllArgsConstructor
@RedisHash("job_view")
public class JobView extends CacheableView{
    private String jobTitle;
    private String teacherId;
    private String courseName;
    private String content;
    private String requirement;
    private String semester;
    private List<LiteJobApplicationView> jobApplicationsView;
}

@Builder
@AllArgsConstructor
class LiteJobApplicationView {
    private String studentId;
    private String applicationState;
}
