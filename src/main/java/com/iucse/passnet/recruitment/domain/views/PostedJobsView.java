package com.iucse.passnet.recruitment.domain.views;

import lombok.Builder;
import lombok.AllArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
public class PostedJobsView {
    private List<LiteJobView> litePostedJobs;
}

@Builder
@AllArgsConstructor
class LiteJobView {
    String courseName;
    String jobTitle;
    String semester;
    String department;
}