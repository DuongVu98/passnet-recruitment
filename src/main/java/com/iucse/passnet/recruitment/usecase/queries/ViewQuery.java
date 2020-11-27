package com.iucse.passnet.recruitment.usecase.queries;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.aggregate.job.entities.JobApplication;
import com.iucse.passnet.recruitment.domain.aggregate.job.vos.JobApplicationId;
import com.iucse.passnet.recruitment.domain.aggregate.job.vos.JobId;
import com.iucse.passnet.recruitment.domain.repositories.JobAggregateRepository;
import com.iucse.passnet.recruitment.domain.viewrepos.JobViewRepository;
import com.iucse.passnet.recruitment.domain.views.JobApplicationView;
import com.iucse.passnet.recruitment.domain.views.JobView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ViewQuery {

    private final JobViewRepository jobViewRepository;
    private final JobAggregateRepository aggregateRepository;

    @Autowired
    public ViewQuery(JobViewRepository jobViewRepository, JobAggregateRepository aggregateRepository) {
        this.jobViewRepository = jobViewRepository;
        this.aggregateRepository = aggregateRepository;
    }

    public JobView queryJobView(String id) {
        Job aggregate = this.aggregateRepository.findByIdWithJobApplications(new JobId(id));
        JobView view = new JobView(aggregate, id);
        this.jobViewRepository.save(view);

        return view;
    }

    public JobApplicationView queryJobApplicationView(String aggregateIdentifier, String id) {
        Job aggregate = this.aggregateRepository.findByIdWithJobApplications(new JobId(aggregateIdentifier));
        JobApplicationView jobApplicationView = new JobApplicationView(aggregate, id);
        
        return jobApplicationView;
    }
}
