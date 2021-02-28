package com.iucse.passnet.recruitment.domain.events.produce;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AcceptStudentApplicationEvent {
	private String jobId;
	private String taId;
}
