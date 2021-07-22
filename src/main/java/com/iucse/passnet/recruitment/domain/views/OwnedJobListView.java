package com.iucse.passnet.recruitment.domain.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OwnedJobListView {
    @Setter
    @JsonProperty("jobs")
    private List<JobLiteView> litePostedJobs;

    @Setter
    @JsonProperty("jobOwner")
    private String teacherId;
}
