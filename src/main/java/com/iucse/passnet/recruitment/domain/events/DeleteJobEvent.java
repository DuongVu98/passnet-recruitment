package com.iucse.passnet.recruitment.domain.events;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeleteJobEvent {
    private String eventId;
    private String jobId;
}
