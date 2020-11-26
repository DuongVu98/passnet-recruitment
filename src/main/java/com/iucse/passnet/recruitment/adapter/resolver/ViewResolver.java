package com.iucse.passnet.recruitment.adapter.resolver;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.aggregate.job.vos.JobId;
import com.iucse.passnet.recruitment.domain.repositories.JobAggregateRepository;
import com.iucse.passnet.recruitment.domain.viewrepos.JobViewRepository;
import com.iucse.passnet.recruitment.domain.views.JobView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ViewResolver {

    private final JobViewRepository jobViewRepository;
    private final JobAggregateRepository aggregateRepository;

    @Autowired
    public ViewResolver(JobViewRepository jobViewRepository, JobAggregateRepository aggregateRepository) {
        this.jobViewRepository = jobViewRepository;
        this.aggregateRepository = aggregateRepository;
    }

    public JobView toJobView(String id) {
        Job aggregate = this.aggregateRepository.findByIdWithJobApplications(new JobId(id));
        JobView view = new JobView(aggregate, id);
        this.jobViewRepository.save(view);

        return view;
    }
}
