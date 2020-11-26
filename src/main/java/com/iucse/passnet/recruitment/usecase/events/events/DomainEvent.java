package com.iucse.passnet.recruitment.usecase.events.events;

import com.iucse.passnet.recruitment.domain.aggregate.job.entities.Job;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DomainEvent {
    private EventTypes eventTypes;
    private Job aggregate;
}
