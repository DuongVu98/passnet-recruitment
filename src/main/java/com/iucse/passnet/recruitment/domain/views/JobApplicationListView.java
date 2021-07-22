package com.iucse.passnet.recruitment.domain.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.iucse.passnet.recruitment.domain.aggregate.entities.Job;
import com.iucse.passnet.recruitment.usecase.mapper.JobApplicationMapper;
import com.iucse.passnet.recruitment.usecase.mapper.JobMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobApplicationListView {
    @JsonProperty("job")
    private JobView jobView;

    @JsonProperty("applications")
    private List<JobApplicationView> jobApplicationViewList;

    public JobApplicationListView(Job aggregate) {
        this.jobView = new JobMapper(aggregate).toJobView();
        this.jobApplicationViewList =
           aggregate
              .getJobApplications()
              .stream()
              .map(jobApplication -> new JobApplicationMapper(jobApplication).toApplicationView())
              .collect(Collectors.toList());
    }
}
