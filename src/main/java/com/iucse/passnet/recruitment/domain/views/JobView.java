package com.iucse.passnet.recruitment.domain.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobView {
    @JsonProperty("jobTitle")
    private String jobTitle;

    @JsonProperty("teacherId")
    private String teacherId;

    @JsonProperty("courseName")
    private String courseName;

    @JsonProperty("content")
    private String content;

    @JsonProperty("requirement")
    private String requirement;

    @JsonProperty("semester")
    private String semester;

    @Setter
    @JsonProperty("jobApplications")
    private List<JobApplicationLiteView> jobApplicationsView;
}
