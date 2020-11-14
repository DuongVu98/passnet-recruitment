package com.iucse.passnet.recruitment.domain.views;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;

@Builder
@AllArgsConstructor
public class JobView {
    private String id;
    private String jobTitle;
    private String teacherId;
    private String courseName;
    private String content;
    private String requirement;
    private String semester;
    private List<JobApplicationView> jobApplications;
}
