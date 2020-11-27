package com.iucse.passnet.recruitment.adapter.controllers;

import com.iucse.passnet.recruitment.usecase.queries.ViewQuery;
import com.iucse.passnet.recruitment.domain.viewrepos.JobViewRepository;
import com.iucse.passnet.recruitment.domain.views.JobView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueryController {

    private final JobViewRepository jobViewRepository;
    private final ViewQuery viewQuery;

    @Autowired
    public QueryController(JobViewRepository jobViewRepository, ViewQuery viewQuery) {
        this.jobViewRepository = jobViewRepository;
        this.viewQuery = viewQuery;
    }

    public JobView getJobView(String id) {
        if (this.jobViewRepository.findById(id).isPresent()) {
            return this.jobViewRepository.findById(id).get();
        }else {
            return this.viewQuery.queryJobView(id);
        }
    }
}
