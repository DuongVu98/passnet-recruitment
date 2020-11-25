package com.iucse.passnet.recruitment.usecase.events.events;

import lombok.Getter;

@Getter
public class DomainEvent {
    private EventTypes eventTypes;
    private EventPayload eventPayload;
}
