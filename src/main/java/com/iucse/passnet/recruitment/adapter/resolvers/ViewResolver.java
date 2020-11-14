package com.iucse.passnet.recruitment.adapter.resolvers;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.aggregate.job.vos.JobId;
import com.iucse.passnet.recruitment.domain.repositories.JobAggregateRepository;
import com.iucse.passnet.recruitment.domain.views.JobApplicationView;
import com.iucse.passnet.recruitment.domain.views.JobView;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ViewResolver implements GraphQLQueryResolver {

    private final JobAggregateRepository viewRepository;

    @Autowired
    public ViewResolver(JobAggregateRepository viewRepository) {
        this.viewRepository = viewRepository;
    }

    public JobView jobView(String id) {
//        JobApplicationView jobApplicationView = JobApplicationView.builder()
//           .content("hello teacher")
//           .letter("hello teacher again")
//           .build();
//        return JobView.builder()
//           .id(id)
//           .jobApplications(List.of(jobApplicationView))
//           .jobTitle("TA for OOP")
//           .build();
        Job job = this.viewRepository.findByIdWithJobApplications(new JobId(id));
        return JobView.builder()
           .id(job.getId().getValue())
           .jobTitle(job.getJobName().getValue())
           .content(job.getContent().getValue())
           .courseName(job.getCourseName().getValue())
           .requirement(job.getJobRequirement().getValue())
           .teacherId(job.getJobOwner().getValue())
           .semester(job.getSemester().getValue())
           .jobApplications(new ArrayList<>())
           .build();
    }

    public JobApplicationView jobApplicationView(String id){
        return JobApplicationView.builder().build();
    }
}
