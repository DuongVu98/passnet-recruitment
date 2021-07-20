package com.iucse.passnet.recruitment.domain.events;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostNewJobEvent {
	private String eventId;
	private String jobId;
	private String ownerId;
}