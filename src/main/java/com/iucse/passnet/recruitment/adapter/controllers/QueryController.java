package com.iucse.passnet.recruitment.adapter.controllers;

import com.iucse.passnet.recruitment.domain.viewrepos.JobApplicationViewRepository;
import com.iucse.passnet.recruitment.domain.views.JobApplicationView;
import com.iucse.passnet.recruitment.usecase.queries.ViewQuery;
import com.iucse.passnet.recruitment.domain.viewrepos.JobViewRepository;
import com.iucse.passnet.recruitment.domain.views.JobView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class QueryController {

    private final JobViewRepository jobViewRepository;
    private final JobApplicationViewRepository jobApplicationViewRepository;
    private final ViewQuery viewQuery;

    @Autowired
    public QueryController(JobViewRepository jobViewRepository, JobApplicationViewRepository jobApplicationViewRepository, ViewQuery viewQuery) {
        this.jobViewRepository = jobViewRepository;
        this.jobApplicationViewRepository = jobApplicationViewRepository;
        this.viewQuery = viewQuery;
    }

    public JobView getJobView(String id) {
        if (this.jobViewRepository.findById(id).isPresent()) {
            return this.jobViewRepository.findById(id).get();
        } else {
            return this.viewQuery.queryJobView(id);
        }
    }

    public JobApplicationView getJobApplicationView(String id) {
        Optional<JobApplicationView> optional = this.jobApplicationViewRepository.findById(id);
        return optional.orElseGet(() -> this.viewQuery.queryJobApplicationView(id));
    }
}
