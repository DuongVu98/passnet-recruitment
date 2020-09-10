package com.iucse.passnet.recruitment.domain.dto;

import lombok.*;

import java.util.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Student extends User {
    private List<String> jobApplicationIds = new ArrayList<>();
    private List<String> acceptedJobApplicationsId = new ArrayList<>();

    @Builder
    public Student(String id, List<String> jobApplicationIds, List<String> acceptedJobApplicationsId) {
        super(id);
        this.jobApplicationIds = jobApplicationIds;
        this.acceptedJobApplicationsId = acceptedJobApplicationsId;
    }

    public void addJobApplication(String jobId) {
        this.jobApplicationIds.add(jobId);
    }

    public void addAcceptedJobApplicationsId(String jobId) {
        acceptedJobApplicationsId.add(jobId);
    }

}
