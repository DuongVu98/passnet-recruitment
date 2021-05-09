package com.iucse.passnet.recruitment.domain.compensating;

import lombok.Getter;

@Getter
public class RemoveJobApplicationCompensating extends CompensatingCommand {
	private String jobId;
	private String jobApplicationId;

	public RemoveJobApplicationCompensating(String eventId, String jobId, String jobApplicationId) {
		super(eventId);
		this.jobId = jobId;
		this.jobApplicationId = jobApplicationId;
	}
}
