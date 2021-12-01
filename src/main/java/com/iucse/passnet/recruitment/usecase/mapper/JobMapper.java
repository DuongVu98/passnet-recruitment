package com.iucse.passnet.recruitment.usecase.mapper;

import com.iucse.passnet.recruitment.domain.aggregate.entities.Job;
import com.iucse.passnet.recruitment.domain.views.JobLiteView;
import com.iucse.passnet.recruitment.domain.views.JobView;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class JobMapper {

    private final Job instance;

    public JobLiteView toLiteView() {
        return JobLiteView
           .builder()
           .id(instance.getId().getValue())
           .jobTitle(instance.getJobName().getValue())
           .semester(instance.getSemester().getValue())
           .courseName(instance.getCourseName().getValue())
           .appliedAmount(instance.getJobApplications().size())
           .postedDate(LocalDate.ofInstant(instance.getCreatedAt(), ZoneOffset.UTC))
           .daysAgo(ChronoUnit.DAYS.between(instance.getCreatedAt(), Instant.now()))
           .build();
    }

    public JobView toJobView() {
        return JobView.builder()
           .jobId(instance.getId().getValue())
           .jobTitle(instance.getJobName().getValue())
           .courseName(instance.getCourseName().getValue())
           .content(instance.getContent().getValue())
           .requirement(instance.getJobRequirement().getValue())
           .semester(instance.getSemester().getValue())
           .teacherId(instance.getJobOwner().getValue())
           .postedDate(LocalDate.ofInstant(instance.getCreatedAt(), ZoneOffset.UTC))
           .daysAgo(ChronoUnit.DAYS.between(instance.getCreatedAt(), Instant.now()))
           .jobApplicationsView(
              instance.getJobApplications().stream()
                 .map(jobApplication -> new JobApplicationMapper(jobApplication).toLiteView())
                 .collect(Collectors.toList())
           )
           .build();
    }
}
