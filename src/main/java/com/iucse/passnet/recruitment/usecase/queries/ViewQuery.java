package com.iucse.passnet.recruitment.usecase.queries;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.domain.aggregate.job.entities.JobApplication;
import com.iucse.passnet.recruitment.domain.aggregate.job.vos.JobApplicationId;
import com.iucse.passnet.recruitment.domain.aggregate.job.vos.JobId;
import com.iucse.passnet.recruitment.domain.annotation.ToCache;
import com.iucse.passnet.recruitment.domain.repositories.JobAggregateRepository;
import com.iucse.passnet.recruitment.domain.repositories.JobApplicationRepository;
import com.iucse.passnet.recruitment.domain.views.JobApplicationView;
import com.iucse.passnet.recruitment.domain.views.JobView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ViewQuery {
    private final JobAggregateRepository jobEntityRepository;
    private final JobApplicationRepository jobApplicationEntityRepository;

    @Autowired
    public ViewQuery(JobAggregateRepository jobEntityRepository, JobApplicationRepository jobApplicationEntityRepository) {
        this.jobEntityRepository = jobEntityRepository;
        this.jobApplicationEntityRepository = jobApplicationEntityRepository;
    }

    @ToCache
    public JobView queryJobView(String id) {
        Job aggregate = this.jobEntityRepository.findByIdWithJobApplications(new JobId(id));
        return new JobView(aggregate, id);
    }

    @ToCache
    public JobApplicationView queryJobApplicationView(String id) {
        Optional<JobApplication> optional = this.jobApplicationEntityRepository.findById(new JobApplicationId(id));
        if(optional.isPresent()){
            JobApplication jobApplication = optional.get();
            return JobApplicationView.builder()
                    .id(id)
                    .studentId(jobApplication.getApplicationOwner().getValue())
                    .content(jobApplication.getContent().getValue())
                    .letter(jobApplication.getLetter().getValue())
                    .state(jobApplication.getApplicationState().getValue().name())
                    .build();
        } else {
            return null;
        }
    }
}
