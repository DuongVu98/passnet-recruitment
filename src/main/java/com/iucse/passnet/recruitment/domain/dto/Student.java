package com.iucse.passnet.recruitment.domain.dto;

import lombok.*;

import java.util.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Student extends User {
    private List<String> jobApplicationIds = new ArrayList<>();

    @Builder
    public Student(String id, List<String> jobApplicationIds) {
        super(id);
        this.jobApplicationIds = jobApplicationIds;
    }

    public void addJobApplication(String jobId) {
        this.jobApplicationIds.add(jobId);
    }
}
