package com.iucse.passnet.recruitment.usecase.queries;

import com.iucse.passnet.recruitment.domain.aggregate.entities.JobApplication;
import com.iucse.passnet.recruitment.domain.aggregate.vos.JobApplicationId;
import com.iucse.passnet.recruitment.domain.aggregate.vos.JobId;
import com.iucse.passnet.recruitment.domain.repositories.JobAggregateRepository;
import com.iucse.passnet.recruitment.domain.repositories.JobApplicationRepository;
import com.iucse.passnet.recruitment.domain.views.*;
import com.iucse.passnet.recruitment.usecase.mapper.JobApplicationMapper;
import com.iucse.passnet.recruitment.usecase.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ViewQuery {
    private final JobAggregateRepository jobEntityRepository;
    private final JobApplicationRepository jobApplicationEntityRepository;

    @Autowired
    public ViewQuery(JobAggregateRepository jobEntityRepository, JobApplicationRepository jobApplicationEntityRepository) {
        this.jobEntityRepository = jobEntityRepository;
        this.jobApplicationEntityRepository = jobApplicationEntityRepository;
    }

    public JobView queryJobView(String id) throws NullPointerException {
        var aggregate = this.jobEntityRepository.findByIdWithJobApplications(new JobId(id));
        return new JobMapper(aggregate).toJobView();
    }

    public JobApplicationView queryJobApplicationView(String id) {
        Optional<JobApplication> optional = this.jobApplicationEntityRepository.findById(new JobApplicationId(id));
        return optional.map(jobApplication -> new JobApplicationMapper(jobApplication).toApplicationView()).orElse(null);
    }

    public PostedJobsView queryPostedJobsView() {
        List<JobLiteView> jobLiteViewList =
           this.jobEntityRepository.findAll()
              .stream()
              .map(job -> new JobMapper(job).toLiteView())
              .collect(Collectors.toList());

        return PostedJobsView.builder().litePostedJobs(jobLiteViewList).build();
    }

    public OwnedJobListView queryUserOwnJob(String uid) {
        List<JobLiteView> jobLiteViewList =
           this.jobEntityRepository.findAll()
              .stream()
              .filter(job -> job.getJobOwner().getValue().equals(uid))
              .map(job -> new JobMapper(job).toLiteView())
              .collect(Collectors.toList());
        return OwnedJobListView.builder().teacherId(uid).litePostedJobs(jobLiteViewList).build();
    }

    public JobApplicationListView queryJobApplicationListView(String jobId) {
        var aggregate = this.jobEntityRepository.findByIdWithJobApplications(new JobId(jobId));
        return new JobApplicationListView(aggregate);
    }
}
