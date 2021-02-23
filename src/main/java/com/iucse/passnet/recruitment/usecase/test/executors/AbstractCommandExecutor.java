package com.iucse.passnet.recruitment.usecase.test.executors;

import com.iucse.passnet.recruitment.domain.repositories.JobAggregateRepository;
import lombok.AllArgsConstructor;
import lombok.Setter;

import javax.transaction.Transactional;

@AllArgsConstructor
public abstract class AbstractCommandExecutor<COMMAND, RETURN> {

    @Setter
    protected JobAggregateRepository aggregateRepository;

    @Transactional
    protected abstract RETURN execute(COMMAND command) throws Throwable;
}
