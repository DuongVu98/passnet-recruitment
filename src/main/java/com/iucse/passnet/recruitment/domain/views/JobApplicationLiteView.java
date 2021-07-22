package com.iucse.passnet.recruitment.domain.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobApplicationLiteView {
	@JsonProperty("studentId")
	private String studentId;

	@JsonProperty("applicationState")
	private String applicationState;
}
