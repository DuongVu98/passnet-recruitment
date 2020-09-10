package com.iucse.passnet.recruitment.usecase.interactors.queries;

import com.iucse.passnet.recruitment.domain.dto.Job;
import com.iucse.passnet.recruitment.domain.mappers.ModelMapper;
import com.iucse.passnet.recruitment.domain.models.JobModel;
import com.iucse.passnet.recruitment.domain.repositories.JobRepository;
import lombok.Builder;

@Builder
public class ViewPostedJobQuery implements ActionQuery<Job> {

    private final String jobId;
    private final JobRepository jobRepository;
    private final ModelMapper<JobModel, Job, String> jobModelMapper;

    private Job viewPostedJob(){
        JobModel jobModel = jobRepository.findFirstById(jobId);
        Job jobDto = jobModelMapper.mapToDto(jobModel);

        return jobDto;
    }

    @Override
    public Job execute() {
        return this.viewPostedJob();
    }
}
