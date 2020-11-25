package com.iucse.passnet.recruitment.usecase.commands.handlers;

import com.iucse.passnet.recruitment.adapter.channel.DomainEventBus;
import com.iucse.passnet.recruitment.domain.repositories.JobAggregateRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class AbstractJobAggregateCommandHandler<AGGREGATE> {
    protected JobAggregateRepository aggregateRepository;

    protected DomainEventBus eventBus;

    public abstract AGGREGATE execute();
}
