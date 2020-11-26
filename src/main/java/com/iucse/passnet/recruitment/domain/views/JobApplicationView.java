package com.iucse.passnet.recruitment.domain.views;

import lombok.Builder;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("job_application_view")
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
}
