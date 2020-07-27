package com.iucse.passnet.recruitment.domain.dto;

import lombok.*;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Teacher extends User {
    private List<String> postedJobsId;

    @Builder
    public Teacher(String id, List<String> postedJobsId) {
        super(id);
        this.postedJobsId = postedJobsId;
    }

    public void addNewPostJob(Job newJob) {
        this.postedJobsId.add(newJob.getId());
    }
}
