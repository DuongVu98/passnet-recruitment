package com.iucse.passnet.recruitment.adapter.controllers;

import com.iucse.passnet.recruitment.adapter.resolver.ViewResolver;
import com.iucse.passnet.recruitment.domain.viewrepos.JobViewRepository;
import com.iucse.passnet.recruitment.domain.views.JobView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

@Component
public class QueryController {

    private final JobViewRepository jobViewRepository;
    private final ViewResolver viewResolver;

    @Autowired
    public QueryController(JobViewRepository jobViewRepository, ViewResolver viewResolver) {
        this.jobViewRepository = jobViewRepository;
        this.viewResolver = viewResolver;
    }

    public JobView getJobView(String id) {
        if (this.jobViewRepository.findById(id).isPresent()) {
            return this.jobViewRepository.findById(id).get();
        }else {
            return this.viewResolver.toJobView(id);
        }
    }
}
