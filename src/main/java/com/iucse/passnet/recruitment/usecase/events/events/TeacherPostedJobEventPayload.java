package com.iucse.passnet.recruitment.usecase.events.events;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import com.iucse.passnet.recruitment.usecase.events.events.EventPayload;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class TeacherPostedJobEventPayload extends EventPayload {
    private Job job;
}
