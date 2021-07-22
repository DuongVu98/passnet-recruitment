package com.iucse.passnet.recruitment.domain.views;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

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
