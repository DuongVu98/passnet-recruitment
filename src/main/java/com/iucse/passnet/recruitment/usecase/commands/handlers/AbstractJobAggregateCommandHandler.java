package com.iucse.passnet.recruitment.usecase.commands.handlers;

import com.iucse.passnet.recruitment.domain.repositories.TestRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class AbstractJobAggregateCommandHandler<AGGREGATE> {
    protected TestRepository aggregateRepository;

    public abstract AGGREGATE execute();
}
