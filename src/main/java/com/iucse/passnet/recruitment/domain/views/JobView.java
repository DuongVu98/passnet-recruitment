package com.iucse.passnet.recruitment.domain.views;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobView {
    @JsonProperty("id")
    private String jobId;

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

    @JsonProperty("postedDate")
    @JsonFormat(pattern = "dd-MM-yyy")
    private LocalDate postedDate;

    @JsonProperty("daysAgo")
    private Long daysAgo;

    @Setter
    @JsonProperty("jobApplications")
    private List<JobApplicationLiteView> jobApplicationsView;
}
