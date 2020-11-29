package com.iucse.passnet.recruitment.usecase.commands.handlers;

import com.iucse.passnet.recruitment.adapter.channel.DomainEventBus;
import com.iucse.passnet.recruitment.domain.repositories.JobAggregateRepository;
import com.iucse.passnet.recruitment.usecase.commands.requests.BaseCommand;
import com.iucse.passnet.recruitment.usecase.events.events.DomainEvent;
import com.iucse.passnet.recruitment.usecase.events.events.EventTypes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public abstract class AbstractJobAggregateCommandHandler<AGGREGATE> {
    @Setter
    protected JobAggregateRepository aggregateRepository;

    @Getter @Setter
    private EventTypes eventToApply;

    @Getter @Setter
    private DomainEventBus eventBus;

    public abstract DomainEvent execute();
}
