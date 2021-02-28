package com.iucse.passnet.recruitment.domain.events.produce;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeleteJobEvent {
	private String jobId;
}
