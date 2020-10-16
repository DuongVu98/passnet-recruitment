package com.iucse.passnet.recruitment.domain.events;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostJobEvent extends Event {
    private String jobId;
}
