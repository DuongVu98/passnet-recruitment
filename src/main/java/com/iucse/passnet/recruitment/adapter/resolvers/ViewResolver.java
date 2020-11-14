package com.iucse.passnet.recruitment.adapter.resolvers;

import com.iucse.passnet.recruitment.domain.views.JobApplicationView;
import com.iucse.passnet.recruitment.domain.views.JobView;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ViewResolver implements GraphQLQueryResolver {

    

    public JobView jobView(String id) {
        JobApplicationView jobApplicationView = JobApplicationView.builder()
           .content("hello teacher")
           .letter("hello teacher again")
           .build();
        return JobView.builder()
           .id(id)
           .jobApplications(List.of(jobApplicationView))
           .jobTitle("TA for OOP")
           .build();
    }

    public JobApplicationView jobApplicationView(String id){
        return JobApplicationView.builder().build();
    }
}
