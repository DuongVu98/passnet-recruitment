package com.iucse.passnet.recruitment.usecase.executors;

import com.iucse.passnet.recruitment.domain.repositories.JobAggregateRepository;
import lombok.AllArgsConstructor;
import lombok.Setter;

import javax.transaction.Transactional;

@AllArgsConstructor
public abstract class AbstractCommandExecutor<COMMAND, RETURN> {

    @Setter
    protected JobAggregateRepository aggregateRepository;

    @Transactional
    public abstract RETURN execute(COMMAND command) throws Throwable;
}
