package com.iucse.passnet.recruitment.adapter.resolvers;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.aggregate.job.vos.JobId;
import com.iucse.passnet.recruitment.domain.repositories.JobAggregateRepository;
import com.iucse.passnet.recruitment.domain.views.JobApplicationView;
import com.iucse.passnet.recruitment.domain.views.JobView;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j(topic = "[ViewResolver]")
public class ViewResolver implements GraphQLQueryResolver {

    private final JobAggregateRepository viewRepository;

    @Autowired
    public ViewResolver(JobAggregateRepository viewRepository) {
        this.viewRepository = viewRepository;
    }

    public JobView jobView(String id) {
        Job job = this.viewRepository.findByIdWithJobApplications(new JobId(id));

        List<JobApplicationView> applicationViews = job.getJobApplications().stream().map(
           application -> {
               return JobApplicationView.builder()
                  .id(application.getId().getValue())
                  .studentId(application.getApplicationOwner().getValue())
                  .letter(application.getLetter().getValue())
                  .content(application.getContent().getValue())
                  .state(application.getApplicationState().getValue().name())
                  .build();
           }
        ).collect(Collectors.toList());

        return JobView.builder()
           .id(job.getId().getValue())
           .jobTitle(job.getJobName().getValue())
           .content(job.getContent().getValue())
           .courseName(job.getCourseName().getValue())
           .requirement(job.getJobRequirement().getValue())
           .teacherId(job.getJobOwner().getValue())
           .semester(job.getSemester().getValue())
           .jobApplications(applicationViews)
           .build();
    }

    public JobApplicationView jobApplicationView(String id) {
        return JobApplicationView.builder().build();
    }
}
