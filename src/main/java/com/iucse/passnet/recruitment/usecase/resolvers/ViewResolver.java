package com.iucse.passnet.recruitment.usecase.resolvers;

import com.iucse.passnet.recruitment.domain.views.JobApplicationView;
import com.iucse.passnet.recruitment.domain.views.JobView;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

@Component
public class ViewResolver implements GraphQLQueryResolver {

    public JobView jobView(String id) {
        return JobView.builder()
           .jobTitle("TA for OOP")
           .build();
    }

    public JobApplicationView jobApplicationView(String id){
        return JobApplicationView.builder().build();
    }
}
