package com.iucse.passnet.recruitment.usecase.mapper;

import com.iucse.passnet.recruitment.domain.aggregate.entities.Job;
import com.iucse.passnet.recruitment.domain.views.JobApplicationLiteView;
import com.iucse.passnet.recruitment.domain.views.JobLiteView;
import com.iucse.passnet.recruitment.domain.views.JobView;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class JobMapper {

    @NonNull
    Job instance;

    public JobLiteView toLiteView() {
        return JobLiteView
           .builder()
           .id(instance.getId().getValue())
           .jobTitle(instance.getJobName().getValue())
           .semester(instance.getSemester().getValue())
           .courseName(instance.getCourseName().getValue())
           .appliedAmount(instance.getJobApplications().size())
           .build();
    }

    public JobView toJobView() {
        return JobView.builder()
           .jobTitle(instance.getJobName().getValue())
           .courseName(instance.getCourseName().getValue())
           .content(instance.getContent().getValue())
           .requirement(instance.getJobRequirement().getValue())
           .semester(instance.getSemester().getValue())
           .teacherId(instance.getJobOwner().getValue())
           .jobApplicationsView(
              instance.getJobApplications().stream()
                 .map(
                    jobApplication -> JobApplicationLiteView.builder()
                       .applicationState(jobApplication.getApplicationState().name())
                       .studentId(jobApplication.getApplicationOwner().getValue())
                       .build()
                 )
                 .collect(Collectors.toList())
           )
           .build();
    }
}
